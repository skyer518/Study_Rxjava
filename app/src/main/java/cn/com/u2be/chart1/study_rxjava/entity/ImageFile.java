package cn.com.u2be.chart1.study_rxjava.entity;

import android.graphics.Bitmap;

/**
 * Created by alek on 2016/6/27.
 */
public class ImageFile {

    private Bitmap bitmap;
    private String fileName;

    public ImageFile() {
    }

    public ImageFile(Bitmap bitmap, String fileName) {
        this.bitmap = bitmap;
        this.fileName = fileName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
