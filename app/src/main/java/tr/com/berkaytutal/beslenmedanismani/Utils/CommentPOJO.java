package tr.com.berkaytutal.beslenmedanismani.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;

/**
 * Created by berka on 21.06.2017.
 */

public class CommentPOJO implements Serializable {

    private byte[] commenterPhoto;
    private int commentID;
    private String commenterName;
    private String commentText;


    public CommentPOJO(byte[] commenterPhoto, int commentID, String commenterName, String commentText) {
        this.commenterPhoto = commenterPhoto;
        this.commentID = commentID;
        this.commenterName = commenterName;
        this.commentText = commentText;
    }


    public Bitmap getCommenterPhoto() {
        return BitmapFactory.decodeByteArray(commenterPhoto, 0, commenterPhoto.length);
    }

    public void setCommenterPhoto(byte[] commenterPhoto) {
        this.commenterPhoto = commenterPhoto;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
