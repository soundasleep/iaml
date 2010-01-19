/**
 * 
 */
package org.openiaml.verification.nusmv;

import it.itc.irst.nusmv.cli.ExecuteNuSMV;
import it.itc.irst.nusmv.cli.ExecuteNuSMV.NuSMVException;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.verification.nusmv.rules.InfiniteOperationLoop;

/**
 * An abstract engine to verify with CrocoPat.
 * This way we can specify different types of verification inputs
 * with different validation rules, etc; similar to Drools.
 * 
 * @author jmwright
 *
 */
public class VerificationEngine {
	
	private List<String> log = new ArrayList<String>();
	private boolean shouldLog;
	
	public VerificationEngine() {
		this(false);
	}
	
	/**
	 * @param shouldLog add logs of verification to {@link #getLog()}.
	 */
	public VerificationEngine(boolean shouldLog) {
		this.shouldLog = shouldLog;
	}
	
	/**
	 * Get all the verification rules to check against.
	 * TODO make this into an extensible method using Eclipse framework
	 * 
	 * @return
	 */
	public List<VerificationRule> getVerificationRules() {
		List<VerificationRule> list = new ArrayList<VerificationRule>();
		list.add(new InfiniteOperationLoop());
		return list;
	}
	
	public List<String> getLog() {
		return this.log;
	}
	
	/**
	 * Verify a given model. Violations can then be obtained by
	 * {@link #getViolations()}.
	 * 
	 * @param model the model to check
	 * @param monitor the monitor to notify progress
	 * @return OK if no violations were found; warning if some were found; error if an exception occured; cancel if the monitor was cancelled
	 * @throws VerificationException
	 */
	public IStatus verify(EObject model, IProgressMonitor monitor) throws VerificationException {
		
		// reset list of violations
		this.violations = new ArrayList<NuSMVViolation>();
		
		List<VerificationRule> rules = getVerificationRules();
		monitor.beginTask("Verifying against " + rules.size() + " rules", rules.size() * 4);
		
		if (rules.isEmpty()) {
			return new Status(Status.WARNING, VerificationNuSMVPlugin.PLUGIN_ID, "No rules found: " + violations.size());
		}
		
		// for each rule, verify it
		for (VerificationRule rule : getVerificationRules()) {
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
			
			try {
			
				monitor.subTask("Exporting model to SMV stream");			
				InputStream modelStream = rule.exportToSMV(model, new SubProgressMonitor(monitor, 1));
				
				monitor.subTask("Loading verification rule stream");
				InputStream ruleStream = rule.getVerificationRule();
				monitor.worked(1);
							
				monitor.subTask("Passing to SMV verification");
				ExecuteNuSMV exec = new ExecuteNuSMV();
				List<String> output = exec.execute( new SequenceInputStream(modelStream, ruleStream) );
				monitor.worked(1);
				
				// add to log?
				if (shouldLog) {
					log.add("Executing rule " + rule + "...");
					log.addAll(output);
				}
				
				monitor.subTask("Parsing SMV results for results");
				List<NuSMVViolation> violations = parseViolations(output);
				this.violations.addAll(violations);
				monitor.worked(1);
				
			} catch (IOException e1) {
				return errorStatus(e1);
			} catch (NuSMVException e1) {
				return errorStatus(e1);
			}
			
		}
		
		// was successful?
		if (!violations.isEmpty()) {
			return new Status(Status.WARNING, VerificationNuSMVPlugin.PLUGIN_ID, "Violations found: " + violations.size());
		}
		
		monitor.done();
		return Status.OK_STATUS;
		
	}
	
	private List<NuSMVViolation> violations;
	
	/**
	 * Get all of the violations found from the last execution of
	 * {@link #verify(EObject)}.
	 * 
	 * @return
	 */
	public List<NuSMVViolation> getViolations() {
		return violations;
	}
	
	/**
	 * From a list of output from Crocopat, generate the list of violations.
	 * 
	 * @param model 
	 * @param results
	 * @param instanceMap 
	 * @return
	 * @throws VerificationException if parsing failed
	 */
	protected List<NuSMVViolation> parseViolations(List<String> results) throws VerificationException {
		
		List<NuSMVViolation> violations = new ArrayList<NuSMVViolation>();
		for (int i = 0; i < results.size(); i++) {
			String s = results.get(i);
			
			// expected format: "-- as demonstrated by the following execution sequence"
			if (s.trim().equals("-- as demonstrated by the following execution sequence")) {
				
				// the rest of the output is the violation message
				StringBuffer buf = new StringBuffer();
				for (i++; i < results.size(); i++) {
					buf.append(results.get(i)).append('\n');
				}
				
				// construct the new violation
				NuSMVViolation v = new NuSMVViolation(buf.toString());
				violations.add(v);
				
			}
		}
		
		// return
		return violations;
	}
	

	public IStatus errorStatus(String message, Throwable e) {
		return new Status(IStatus.ERROR, VerificationNuSMVPlugin.PLUGIN_ID, message, e);
	}
	
	public IStatus errorStatus(Throwable e) {
		return new Status(IStatus.ERROR, VerificationNuSMVPlugin.PLUGIN_ID, e.getMessage(), e);
	}

}
