package in.adityatelange.imageclip;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class ClipboardImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the shared image from the intent
        Intent intent = getIntent();
        if (Intent.ACTION_SEND.equals(intent.getAction()) && intent.getType() != null) {
            if (intent.getType().startsWith("image/")) {
                Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                if (imageUri != null) {
                    copyImageToClipboard(imageUri);
                }
            }
        }

        // Close the activity after processing
        finish();
    }

    private void copyImageToClipboard(Uri imageUri) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (clipboard != null) {
            ContentResolver resolver = getContentResolver();
            ClipData clip = ClipData.newUri(resolver, "Shared Image", imageUri);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Image copied to clipboard!", Toast.LENGTH_SHORT).show();
        }
    }
}
