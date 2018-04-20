package com.example.sproj.sproj;

public class ProximityCode {


}



//    ProximityZone zone1 = proximityObserver.zoneBuilder()
//                .forAttachmentKeyAndValue("floor", "1st")
//                .inCustomRange(1)
//                .withOnEnterAction(new Function1<ProximityAttachment, Unit>() {
//                    @Override
//                    public Unit invoke(ProximityAttachment attachment) {
//                        Toast.makeText(MainActivity.this, "bluetooth beacon detected",
//                                Toast.LENGTH_SHORT).show();
//                        Log.d("app", "Welcome to the 1st floor");
//                        return null;
//                    }
//                })
//                .withOnExitAction(new Function1<ProximityAttachment, Unit>() {
//                    @Override
//                    public Unit invoke(ProximityAttachment attachment) {
//                        Log.d("app", "Bye bye, come visit us again on the 1st floor");
//                        Toast.makeText(MainActivity.this, "bluetooth beacon left",
//                                Toast.LENGTH_SHORT).show();
//                        return null;
//                    }
//                })
//                .withOnChangeAction(new Function1<List<? extends ProximityAttachment>, Unit>() {
//                    @Override
//                    public Unit invoke(List<? extends ProximityAttachment> proximityAttachments) {
//                            /* Do something here */
//                        return null;
//                    }
//                })
//                .create();
//        ProximityObserver.Handler observationHandler =
//                proximityObserver
//                        .addProximityZone(zone1)
//                        .start();
////        this.proximityObserver.addProximityZone(zone1);



//
//        ProximityZone mint = proximityObserver.zoneBuilder()
//                .forAttachmentKeyAndValue("desk", "mint")
//                .inCustomRange(1)
//                .withOnEnterAction(new Function1<ProximityAttachment, Unit>() {
//                    @Override
//                    public Unit invoke(ProximityAttachment attachment) {
//                        Toast.makeText(MainActivity.this, "Welcome to blueberry desk",
//                                Toast.LENGTH_SHORT).show();
//                        Log.d("app", "Welcome to blueberry desk");
//                        return null;
//                    }
//                })
//                .withOnExitAction(new Function1<ProximityAttachment, Unit>() {
//                    @Override
//                    public Unit invoke(ProximityAttachment attachment) {
//                        Log.d("app", "Bye bye, come visit us again on the blueberry desk");
//                        Toast.makeText(MainActivity.this, "Bye bye from blueberry desk",
//                                Toast.LENGTH_SHORT).show();
//                        return null;
//                    }
//                })
//                .withOnChangeAction(new Function1<List<? extends ProximityAttachment>, Unit>() {
//                    @Override
//                    public Unit invoke(List<? extends ProximityAttachment> proximityAttachments) {
//                            /* Do something here */
//                        return null;
//                    }
//                })
//                .create();
//        ProximityObserver.Handler observationHandler1 =
//                proximityObserver
//                        .addProximityZone(mint)
//                        .start();
//









//        RequirementsWizardFactory
//                .createEstimoteRequirementsWizard()
//                .fulfillRequirements(MainActivity.this,
//                        // onRequirementsFulfilled
//                        new Function0<Unit>() {
//                            @Override public Unit invoke() {
//                                Log.d("app", "requirements fulfilled");
//                                proximityObserver.start();
//                                return null;
//                            }
//                        },
//                        // onRequirementsMissing
//                        new Function1<List<? extends Requirement>, Unit>() {
//                            @Override public Unit invoke(List<? extends Requirement> requirements) {
//                                Log.e("app", "requirements missing: " + requirements);
//                                return null;
//                            }
//                        },
//                        // onError
//                        new Function1<Throwable, Unit>() {
//                            @Override public Unit invoke(Throwable throwable) {
//                                Log.e("app", "requirements error: " + throwable);
//                                return null;
//                            }
//                        });
//        mAuth = FirebaseAuth.getInstance();
//        user = mAuth.getCurrentUser();
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//
//        TextView myTextView = (TextView) findViewById(R.id.textView);
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "Fonts/TitilliumWeb-Black.ttf");
//        image = (ImageView)findViewById(R.id.imagedb);
//        myTextView.setTypeface(typeface);
//        // Successfully downloaded data to local file
//        // ...
//        // Load the image using Glide
////        Glide.with(this /* context */)
////                .using(new FirebaseImageLoader())
////                .load(mStorageRef)
////                .into(image);
//
//        // Sound code here
//        final MediaPlayer waterMP = MediaPlayer.create(this, R.raw.paani);
//
//        ImageButton waterSound = (ImageButton) this.findViewById(R.id.image1);
//
//        waterSound.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                waterMP.start();
//            }
//        });
//
//        if (user != null) {
//            user.reload();
//            Toast.makeText(MainActivity.this, "user signed in.",
//                    Toast.LENGTH_SHORT).show();
//        } else {
//            mAuth.signInAnonymously()
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
////                                Log.d(TAG, "signInAnonymously:success");
//                                user = mAuth.getCurrentUser();
//                                try {
//                                    final File localFile = File.createTempFile("images", "jpg");
//                                    mStorageRef.child("bg2.jpg").getFile(localFile)
//                                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                                                @Override
//                                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                                                    Toast.makeText(MainActivity.this, "In storage on success.",
//                                                            Toast.LENGTH_SHORT).show();
//                                                    image.setImageDrawable(Drawable.createFromPath(localFile.getPath()));
//
//                                                }
//                                            }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception exception) {
//                                            // Handle failed download
//                                            // ...
//                                        }
//                                    });
//                                } catch (IOException e) {
//                                    Toast.makeText(MainActivity.this, "In storage catch.",
//                                            Toast.LENGTH_SHORT).show();
//                                    e.printStackTrace();
//                                }
//                            } else {
//                                // If sign in fails, display a message to the user.
////                                Log.w(TAG, "signInAnonymously:failure", task.getException());
//                                Toast.makeText(MainActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//
//                            // ...
//                        }
//                    });
//        }
//        try {
//            final File localFile = File.createTempFile("images", "png");
//            mStorageRef.child("inst2.png").getFile(localFile)
//                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                            Bitmap bmp = BitmapFactory.decodeFile(localFile.getAbsolutePath());
//                            image.setImageBitmap(bmp);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle failed download
//                    // ...
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Intent intent = new Intent(this,StudentListFragment.class);
//        startActivity(intent);

