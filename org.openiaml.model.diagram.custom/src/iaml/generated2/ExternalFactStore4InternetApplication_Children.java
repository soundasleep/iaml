package iaml.generated2;

/**
 * Class generated by the take compiler.
 * This class represents the external fact store factsInternetApplication_Children
 * for the predicate app_children
 * @version Wed Oct 15 14:04:32 NZDT 2008
 */
public interface ExternalFactStore4InternetApplication_Children {
    // Get all instances of this type from the fact store. 
    public nz.org.take.rt.ResourceIterator<iaml.generated2.InternetApplication_Children> fetch(
        org.openiaml.model.model.InternetApplication slot1,
        org.openiaml.model.model.visual.Page slot2);
}
