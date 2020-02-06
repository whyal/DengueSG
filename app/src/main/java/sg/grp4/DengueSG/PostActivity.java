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
 import android.widget.TextView;
 import android.widget.Toast;

 import com.google.android.gms.tasks.OnFailureListener;
 import com.google.android.gms.tasks.OnSuccessListener;
 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.auth.FirebaseUser;
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

    Button publishPost, ButtonChooseOptionForImage, closeBtn;
    EditText text;
    ImageView imgView;
    String UserN;
    User firstName = User.getInstance();

    private Uri imgUri;

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    DatabaseReference mDatabaseReference, mUserRef;
    StorageReference mStorageReference;
    FirebaseDatabase mFirebaseDatabase;

    StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        //FindViewById(s)
        text = findViewById(R.id.textALL);
        publishPost = findViewById(R.id.publish);
        ButtonChooseOptionForImage = findViewById(R.id.chooseImg);
        closeBtn = findViewById(R.id.cancelBtn);
        //progBar = findViewById(R.id.progressBar);
        imgView = findViewById(R.id.chosen_img);

        //Firebase References
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Posts");
        mStorageReference = FirebaseStorage.getInstance().getReference("Posts");

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mUserRef = FirebaseDatabase.getInstance().getReference("Users");

        //OnClickListeners
        ButtonChooseOptionForImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomOption bottomOption = new BottomOption();
                bottomOption.show(getSupportFragmentManager(), "bottomOption");
            }
        });

        publishPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(PostActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //Method to pick images from gallery
    public void openFileChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    //Method to open the camera
    public void openCamera() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 0);
    }

    //
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                 && data != null && data.getData() != null) {
             imgUri = data.getData();

             Picasso.get().load(imgUri).fit().centerCrop().into(imgView);
         }
     }

     private String getFileExtension(Uri uri) {
         ContentResolver cR = getContentResolver();
         MimeTypeMap mime = MimeTypeMap.getSingleton();
         return mime.getExtensionFromMimeType(cR.getType(uri));
     }

     private void uploadFile() {
        if (imgUri != null) {
            StorageReference fileRef = mStorageReference.child(System.currentTimeMillis()
            + "." + getFileExtension(imgUri));

            DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Posts").child(mFirebaseUser.getUid()).child("user");

            //firstName.setFirstName();

            mUploadTask = fileRef.putFile(imgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //progBar.setProgress(0);
                                }
                            }, 500);
                            String firstname = firstName.getFirstName();
                            Toast.makeText(PostActivity.this, firstname, Toast.LENGTH_SHORT).show();

                            Toast.makeText(PostActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
                            Post post = new Post(text.getText().toString().trim(),
                                    mStorageReference.getDownloadUrl().toString(),
                                    mUserRef.child(mFirebaseUser.getUid()).getKey());
                            String postId = mDatabaseReference.push().getKey();
                            mDatabaseReference.child(postId).setValue(post);
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
                            //progBar.setProgress((int) progress);
                        }
                    });
        }
        else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }

     }
}
