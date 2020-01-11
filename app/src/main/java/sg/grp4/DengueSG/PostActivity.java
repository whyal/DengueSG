 package sg.grp4.DengueSG;

 import android.content.ContentResolver;
 import android.content.Intent;
 import android.net.Uri;
 import android.os.Bundle;
 import android.os.Handler;
 import android.provider.MediaStore;
 import android.support.annotation.NonNull;
 import android.support.v7.app.AppCompatActivity;
 import android.view.View;
 import android.webkit.MimeTypeMap;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.ImageView;
 import android.widget.ProgressBar;
 import android.widget.Toast;

 import com.google.android.gms.tasks.OnFailureListener;
 import com.google.android.gms.tasks.OnSuccessListener;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.storage.FirebaseStorage;
 import com.google.firebase.storage.OnProgressListener;
 import com.google.firebase.storage.StorageReference;
 import com.google.firebase.storage.StorageTask;
 import com.google.firebase.storage.UploadTask;
 import com.squareup.picasso.Picasso;

 public class PostActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText text;
    Button ButtonAdd, ButtonChooseOptionForImage;
    ImageView imgView;
    ProgressBar progBar;

    private Uri imgUri;

    DatabaseReference dbRef;
    StorageReference srRef;

    StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        text = findViewById(R.id.textALL);
        ButtonAdd = findViewById(R.id.publishBtn);
        ButtonChooseOptionForImage = findViewById(R.id.chooseImg);
        progBar = findViewById(R.id.progressBar);
        imgView = findViewById(R.id.chosen_img);

        dbRef = FirebaseDatabase.getInstance().getReference("Testing");
        srRef = FirebaseStorage.getInstance().getReference("Testing");

        ButtonChooseOptionForImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomOption bottomOption = new BottomOption();
                bottomOption.show(getSupportFragmentManager(), "bottomOption");
            }
        });


        ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(PostActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });
    }

    public void openFileChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    public void openCamera() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 0);
    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                 && data != null && data.getData() != null) {
             imgUri = data.getData();

             Picasso.get().load(imgUri).into(imgView);
         }
     }

     private String getFileExtension(Uri uri) {
         ContentResolver cR = getContentResolver();
         MimeTypeMap mime = MimeTypeMap.getSingleton();
         return mime.getExtensionFromMimeType(cR.getType(uri));
     }

     private void uploadFile() {
        if (imgUri != null) {
            StorageReference fileRef = srRef.child(System.currentTimeMillis()
            + "." + getFileExtension(imgUri));

            mUploadTask = fileRef.putFile(imgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(PostActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
                            Post post = new Post(text.getText().toString().trim(),
                                    srRef.getDownloadUrl().toString());
                            String postId = dbRef.push().getKey();
                            dbRef.child(postId).setValue(post);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PostActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progBar.setProgress((int) progress);
                        }
                    });
        }
        else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
     }
}
