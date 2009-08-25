<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm name="0">
	<cp>
		<constant value="simple2rdf"/>
		<constant value="links"/>
		<constant value="NTransientLinkSet;"/>
		<constant value="col"/>
		<constant value="J"/>
		<constant value="main"/>
		<constant value="A"/>
		<constant value="OclParametrizedType"/>
		<constant value="#native"/>
		<constant value="Collection"/>
		<constant value="J.setName(S):V"/>
		<constant value="OclSimpleType"/>
		<constant value="OclAny"/>
		<constant value="J.setElementType(J):V"/>
		<constant value="TransientLinkSet"/>
		<constant value="A.rdfGraph():V"/>
		<constant value="A.__matcher__():V"/>
		<constant value="A.__exec__():V"/>
		<constant value="self"/>
		<constant value="__resolve__"/>
		<constant value="1"/>
		<constant value="J.oclIsKindOf(J):B"/>
		<constant value="18"/>
		<constant value="NTransientLinkSet;.getLinkBySourceElement(S):QNTransientLink;"/>
		<constant value="J.oclIsUndefined():B"/>
		<constant value="15"/>
		<constant value="NTransientLink;.getTargetFromSource(J):J"/>
		<constant value="17"/>
		<constant value="30"/>
		<constant value="Sequence"/>
		<constant value="2"/>
		<constant value="A.__resolve__(J):J"/>
		<constant value="QJ.including(J):QJ"/>
		<constant value="QJ.flatten():QJ"/>
		<constant value="e"/>
		<constant value="value"/>
		<constant value="resolveTemp"/>
		<constant value="S"/>
		<constant value="NTransientLink;.getNamedTargetFromSource(JS):J"/>
		<constant value="name"/>
		<constant value="__matcher__"/>
		<constant value="__exec__"/>
		<constant value="rdfGraph"/>
		<constant value="RDFGraph"/>
		<constant value="rdf"/>
		<constant value="graph"/>
		<constant value="12:3-12:13"/>
		<constant value="12:23-12:24"/>
		<constant value="12:3-12:25"/>
		<constant value="11:2-13:3"/>
		<constant value="r"/>
	</cp>
	<field name="1" type="2"/>
	<field name="3" type="4"/>
	<operation name="5">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<push arg="7"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="9"/>
			<call arg="10"/>
			<dup/>
			<push arg="11"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="12"/>
			<call arg="10"/>
			<call arg="13"/>
			<set arg="3"/>
			<getasm/>
			<push arg="14"/>
			<push arg="8"/>
			<new/>
			<set arg="1"/>
			<getasm/>
			<call arg="15"/>
			<getasm/>
			<call arg="16"/>
			<getasm/>
			<call arg="17"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="18" begin="0" end="26"/>
		</localvariabletable>
	</operation>
	<operation name="19">
		<context type="6"/>
		<parameters>
			<parameter name="20" type="4"/>
		</parameters>
		<code>
			<load arg="20"/>
			<getasm/>
			<get arg="3"/>
			<call arg="21"/>
			<if arg="22"/>
			<getasm/>
			<get arg="1"/>
			<load arg="20"/>
			<call arg="23"/>
			<dup/>
			<call arg="24"/>
			<if arg="25"/>
			<load arg="20"/>
			<call arg="26"/>
			<goto arg="27"/>
			<pop/>
			<load arg="20"/>
			<goto arg="28"/>
			<push arg="29"/>
			<push arg="8"/>
			<new/>
			<load arg="20"/>
			<iterate/>
			<store arg="30"/>
			<getasm/>
			<load arg="30"/>
			<call arg="31"/>
			<call arg="32"/>
			<enditerate/>
			<call arg="33"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="34" begin="23" end="27"/>
			<lve slot="0" name="18" begin="0" end="29"/>
			<lve slot="1" name="35" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="36">
		<context type="6"/>
		<parameters>
			<parameter name="20" type="4"/>
			<parameter name="30" type="37"/>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<load arg="20"/>
			<call arg="23"/>
			<load arg="20"/>
			<load arg="30"/>
			<call arg="38"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="18" begin="0" end="6"/>
			<lve slot="1" name="35" begin="0" end="6"/>
			<lve slot="2" name="39" begin="0" end="6"/>
		</localvariabletable>
	</operation>
	<operation name="40">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="18" begin="0" end="-1"/>
		</localvariabletable>
	</operation>
	<operation name="41">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="18" begin="0" end="-1"/>
		</localvariabletable>
	</operation>
	<operation name="42">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="43"/>
			<push arg="44"/>
			<new/>
			<store arg="20"/>
			<load arg="20"/>
			<pop/>
			<getasm/>
			<load arg="20"/>
			<set arg="45"/>
		</code>
		<linenumbertable>
			<lne id="46" begin="6" end="6"/>
			<lne id="47" begin="7" end="7"/>
			<lne id="48" begin="6" end="8"/>
			<lne id="49" begin="6" end="8"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="50" begin="3" end="8"/>
			<lve slot="0" name="18" begin="0" end="8"/>
		</localvariabletable>
	</operation>
</asm>
