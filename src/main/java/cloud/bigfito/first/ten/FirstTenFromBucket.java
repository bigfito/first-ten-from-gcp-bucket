package cloud.bigfito.first.ten;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Adolfo Orozco - bigfito@gmail.com
 * This class is meant to retrieve and print the first 10 file objects
 * from a public Google Cloud Storage bucket from a GCP project.
 */
public class FirstTenFromBucket {

    /**
     * This method retrieves all stored objects from a public bucket belonging to a GCP project
     *
     * @param gcpProjectID      A string representing the project ID of the GCP project
     * @param gcpBucketName     A string representing the bucket name of the GCP project
     * @return                  A list with all objects from the bucket of the GCP project
     */
    public static List<BucketElement> getElementsFromBucket(String gcpProjectID, String gcpBucketName){

        // A list to store all elements from the GCP bucket
        List<BucketElement> elementsFromBucket = new ArrayList<>();

        // GCP storage magic
        Storage storage = StorageOptions.newBuilder().setProjectId(gcpProjectID).build().getService();
        Page<Blob> blobs = storage.list(gcpBucketName);

        // Adding all elements to the list
        for ( Blob blob : blobs.iterateAll() ) {
            elementsFromBucket.add( new BucketElement( blob.getName(), blob.getSize() ) );
        }

        return elementsFromBucket;
    }

    /**
     * This method prepares the first 10 file objects from the GCP bucket sorted by size in DESC order
     *
     * @param bucketElementsToFilter    A reference to the list of elements to filter
     * @return                          A list with the resulting 10 file objects sorted by file size
     */
    public static List<BucketElement> getFirstTenElementsFromBucket(List<BucketElement> bucketElementsToFilter){

        return bucketElementsToFilter.stream()
                .sorted( (e1,e2) -> e2.getBucketFilesize().compareTo( e1.getBucketFilesize() )  )
                .limit(10)
                .collect(Collectors.toList());
    }

    /**
     * This method prints the contents of the list of file objects received as parameter
     *
     * @param bucketElementsToPrint    A reference to the list of elements to print
     */
    public static void printBucketElements(List<BucketElement> bucketElementsToPrint){

        // iteration index
        int i = 1;

        for ( BucketElement be : bucketElementsToPrint ){
            System.out.println("Filename(" + i  + "):" + be.getBucketFilename() + " Size: " + be.getBucketFilesize() );
            i++;
        }
    }

    /**
     * main() method of the Main class
     *
     * @param args  Default args parameter
     */
    public static void main(String[] args) {

        // A list of type BucketElement to store all elements from the bucket
        List<BucketElement> elementsFromBucket;

        elementsFromBucket = getElementsFromBucket("data-engineer-gsd","bigfito-data-engineer-programming-challenge");
        elementsFromBucket = getFirstTenElementsFromBucket(elementsFromBucket);
        printBucketElements(elementsFromBucket);
    }

}
