package cloud.bigfito.first.ten;

/**
 * @author Adolfo Orozco - bigfito@gmail.com
 * This class is meant to model an object from a Cloud Storage Bucket from GCP
 */
public class BucketElement {

    // Filename of file object from GCP bucket
    private String bucketFilename;
    
    // Size of file object from GCP bucket
    private Long bucketFilesize;

    /* Constructor of class */
    public BucketElement(String bucketFilename, Long bucketFilesize){

        this.bucketFilename = bucketFilename;
        this.bucketFilesize = bucketFilesize;
    }

    // Getter to retrieve the filename of the bucket object
    public String getBucketFilename() {
        return bucketFilename;
    }

    // Setter to set the filename of the bucket object
    public void setBucketFilename(String bucketFilename) {
        this.bucketFilename = bucketFilename;
    }

    // Getter to retrieve the file size of the bucket object
    public Long getBucketFilesize() {
        return bucketFilesize;
    }

    // Setter to set the file size of the bucket object
    public void setBucketFilesize(Long bucketFilesize) {
        this.bucketFilesize = bucketFilesize;
    }

}
