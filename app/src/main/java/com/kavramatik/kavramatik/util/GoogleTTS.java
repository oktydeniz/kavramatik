package com.kavramatik.kavramatik.util;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import com.kavramatik.kavramatik.R;

import java.util.Locale;

public class GoogleTTS {
    public static void getSpeech(String s, Context context, int status, TextToSpeech speech) {

        if (status == TextToSpeech.SUCCESS) {
            speech.setLanguage(Locale.getDefault());
            Locale locale = new Locale("tr", "TR");
            int result = speech.setLanguage(locale);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context, context.getResources().getText(R.string.language_not_supported), Toast.LENGTH_LONG).show();
            } else {
                speech.speak(s, TextToSpeech.QUEUE_FLUSH, null, "");
            }
        }

    }
}
