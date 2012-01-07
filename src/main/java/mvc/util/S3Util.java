package mvc.util;

import java.io.ByteArrayInputStream;
import org.jets3t.service.S3Service;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.web.multipart.MultipartFile;

public class S3Util {

    private String s3AccessKey;
    private String s3SecretKey;
    private String s3Bucket;
    
    public void setS3AccessKey(String s3AccessKey) {
        this.s3AccessKey = s3AccessKey;
    }

    public void setS3SecretKey(String s3SecretKey) {
        this.s3SecretKey = s3SecretKey;
    }

    public void setS3Bucket(String s3Bucket) {
        this.s3Bucket = s3Bucket;
    }
      
    public boolean saveImage(MultipartFile image, long id) {
     
        try {
            if (image.isEmpty()) {
                return false;
            }

            if (!image.getContentType().equals("image/jpeg")) {
                return false;
            }

            AWSCredentials cred = new AWSCredentials(s3AccessKey, s3SecretKey);
            S3Service s3 = new RestS3Service(cred);
            
            S3Bucket imageBucket = s3.getBucket(s3Bucket);
            S3Object imageObject = new S3Object(id + ".jpg");
            imageObject.setDataInputStream(new ByteArrayInputStream(image.getBytes()));
            imageObject.setContentLength(image.getBytes().length);
            imageObject.setContentType("image/jpeg");

            AccessControlList acl = new AccessControlList();
            acl.setOwner(imageBucket.getOwner());
            acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
            imageObject.setAcl(acl);

            s3.putObject(imageBucket, imageObject);

        } catch (Exception ex) {

            System.out.println("prkl");
            return false;
        }

        System.out.println("go!");
        return true;
    }
    
   public boolean saveFile(String content, String name) {
     
        try {
            AWSCredentials cred = new AWSCredentials(s3AccessKey, s3SecretKey);
            S3Service s3 = new RestS3Service(cred);
            
            S3Bucket imageBucket = s3.getBucket(s3Bucket);
            S3Object imageObject = new S3Object(name);
            imageObject.setDataInputStream(new ByteArrayInputStream(content.getBytes()));
            imageObject.setContentLength(content.getBytes().length);
            imageObject.setContentType("text/plain");

            AccessControlList acl = new AccessControlList();
            acl.setOwner(imageBucket.getOwner());
            acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
            imageObject.setAcl(acl);

            s3.putObject(imageBucket, imageObject);

        } catch (Exception ex) {
            System.out.println("prkl");
            return false;
        }

        System.out.println("go!");
        return true;
    }    
}
