package cn.com.u2be.chart1.study_rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.u2be.chart1.study_rxjava.adapter.GridAdapter;
import cn.com.u2be.chart1.study_rxjava.entity.ImageFile;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RecyclerGridActivity extends AppCompatActivity {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_grid);
        ButterKnife.inject(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new GridAdapter();
        recyclerView.setAdapter(adapter);

        File folderDCIM = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File file = new File(folderDCIM, "Camera");

        loadImages(new File[]{file});
    }


    public void loadImages(File[] folders) {

        Observable.from(folders).flatMap(new Func1<File, Observable<File>>() {
            @Override
            public Observable<File> call(File file) {
                return Observable.from(file.listFiles());
            }
        }).filter(new Func1<File, Boolean>() {
            @Override
            public Boolean call(File file) {
                return file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg");
            }
        }).map(new Func1<File, ImageFile>() {
            @Override
            public ImageFile call(File file) {
                return getBitmapFromFile(file);
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ImageFile>() {
                    @Override
                    public void call(ImageFile imageFile) {
                        adapter.addItem(imageFile);
                        adapter.notifyDataSetChanged();
                    }
                });
    }


    private ImageFile getBitmapFromFile(File file) {
        Bitmap bitmap;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 10;
            bitmap = BitmapFactory.decodeStream(fileInputStream, null, options);
            return new ImageFile(bitmap, file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
