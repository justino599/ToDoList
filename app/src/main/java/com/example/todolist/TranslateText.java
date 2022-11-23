package com.example.todolist;

// Imports the Google Cloud Translation library.

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;


public class TranslateText {
    private boolean connected;
    private Translate translate;
    private final Context context;
    private int charsTranslated;
    private String language;
    private static ArrayList<String[]> languages;

    public TranslateText(Context context, String language) {
        this.context = context;
        loadLanguages();
        setLanguage(language);
        getTranslateService();
    }

    public TranslateText(Context context) {
        this(context, "en");
    }

    private void setLanguage(String language) {
        this.language = getLanguageCode(language);
    }

    private String getLanguageCode(String language) {
        language = language.toLowerCase(Locale.ROOT);
        for (String[] languageTuple : languages) {
            if (languageTuple[0].equalsIgnoreCase(language) || languageTuple[1].equalsIgnoreCase(language))
                return languageTuple[1];
        }

        throw new IllegalArgumentException(language + " is not a valid language");
    }

    public String[] getLanguages() {
        String[] out = new String[languages.size()];
        for (int i = 0; i < languages.size(); i++)
            out[i] = languages.get(i)[0];
        return out;
    }

    private void getTranslateService() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // The credentials.json file being loaded is not added to Git because I don't want to leak my key to a public repo
        try (InputStream is = context.getResources().openRawResource(R.raw.credentials)) {
            //Get credentials:
            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);

            //Set credentials and get translate service:
            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
            translate = translateOptions.getService();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String translate(String text, String language) {
        language = getLanguageCode(language);
        if (charsTranslated < 1000) {
            charsTranslated += text.length();
            return translate.translate(text, Translate.TranslateOption.targetLanguage(language), Translate.TranslateOption.model("base")).getTranslatedText();
        } else {
            return "FAILSAFE: Too many characters translated";
        }
    }

    public String translate(String text) {
        return translate(text, language);
    }

    public boolean checkInternetConnection() {

        //Check internet connection:
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //Means that we are connected to a network (mobile or wi-fi)
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        return connected;
    }

    private void loadLanguages() {
        languages = new ArrayList<>();
        languages.add(new String[]{"Afrikaans","af"});
        languages.add(new String[]{"Albanian","sq"});
        languages.add(new String[]{"Amharic","am"});
        languages.add(new String[]{"Arabic","ar"});
        languages.add(new String[]{"Armenian","hy"});
        languages.add(new String[]{"Assamese","as"});
        languages.add(new String[]{"Aymara","ay"});
        languages.add(new String[]{"Azerbaijani","az"});
        languages.add(new String[]{"Bambara","bm"});
        languages.add(new String[]{"Basque","eu"});
        languages.add(new String[]{"Belarusian","be"});
        languages.add(new String[]{"Bengali","bn"});
        languages.add(new String[]{"Bhojpuri","bho"});
        languages.add(new String[]{"Bosnian","bs"});
        languages.add(new String[]{"Bulgarian","bg"});
        languages.add(new String[]{"Catalan","ca"});
        languages.add(new String[]{"Cebuano","ceb"});
        languages.add(new String[]{"Chinese Simplified)","zh"});
        languages.add(new String[]{"Chinese Traditional)","zh-TW"});
        languages.add(new String[]{"Corsican","co"});
        languages.add(new String[]{"Croatian","hr"});
        languages.add(new String[]{"Czech","cs"});
        languages.add(new String[]{"Danish","da"});
        languages.add(new String[]{"Dhivehi","dv"});
        languages.add(new String[]{"Dogri","doi"});
        languages.add(new String[]{"Dutch","nl"});
        languages.add(new String[]{"English","en"});
        languages.add(new String[]{"Esperanto","eo"});
        languages.add(new String[]{"Estonian","et"});
        languages.add(new String[]{"Ewe","ee"});
        languages.add(new String[]{"Filipino (Tagalog)","fil"});
        languages.add(new String[]{"Finnish","fi"});
        languages.add(new String[]{"French","fr"});
        languages.add(new String[]{"Frisian","fy"});
        languages.add(new String[]{"Galician","gl"});
        languages.add(new String[]{"Georgian","ka"});
        languages.add(new String[]{"German","de"});
        languages.add(new String[]{"Greek","el"});
        languages.add(new String[]{"Guarani","gn"});
        languages.add(new String[]{"Gujarati","gu"});
        languages.add(new String[]{"Haitian Creole","ht"});
        languages.add(new String[]{"Hausa","ha"});
        languages.add(new String[]{"Hawaiian","haw"});
        languages.add(new String[]{"Hebrew","he"});
        languages.add(new String[]{"Hindi","hi"});
        languages.add(new String[]{"Hmong","hmn"});
        languages.add(new String[]{"Hungarian","hu"});
        languages.add(new String[]{"Icelandic","is"});
        languages.add(new String[]{"Igbo","ig"});
        languages.add(new String[]{"Ilocano","ilo"});
        languages.add(new String[]{"Indonesian","id"});
        languages.add(new String[]{"Irish","ga"});
        languages.add(new String[]{"Italian","it"});
        languages.add(new String[]{"Japanese","ja"});
        languages.add(new String[]{"Javanese","jw"});
        languages.add(new String[]{"Kannada","kn"});
        languages.add(new String[]{"Kazakh","kk"});
        languages.add(new String[]{"Khmer","km"});
        languages.add(new String[]{"Kinyarwanda","rw"});
        languages.add(new String[]{"Konkani","gom"});
        languages.add(new String[]{"Korean","ko"});
        languages.add(new String[]{"Krio","kri"});
        languages.add(new String[]{"Kurdish","ku"});
        languages.add(new String[]{"Kurdish (Sorani)","ckb"});
        languages.add(new String[]{"Kyrgyz","ky"});
        languages.add(new String[]{"Lao","lo"});
        languages.add(new String[]{"Latin","la"});
        languages.add(new String[]{"Latvian","lv"});
        languages.add(new String[]{"Lingala","ln"});
        languages.add(new String[]{"Lithuanian","lt"});
        languages.add(new String[]{"Luganda","lg"});
        languages.add(new String[]{"Luxembourgish","lb"});
        languages.add(new String[]{"Macedonian","mk"});
        languages.add(new String[]{"Maithili","mai"});
        languages.add(new String[]{"Malagasy","mg"});
        languages.add(new String[]{"Malay","ms"});
        languages.add(new String[]{"Malayalam","ml"});
        languages.add(new String[]{"Maltese","mt"});
        languages.add(new String[]{"Maori","mi"});
        languages.add(new String[]{"Marathi","mr"});
        languages.add(new String[]{"Meiteilon (Manipuri)","mni-Mtei"});
        languages.add(new String[]{"Mizo","lus"});
        languages.add(new String[]{"Mongolian","mn"});
        languages.add(new String[]{"Myanmar (Burmese)","my"});
        languages.add(new String[]{"Nepali","ne"});
        languages.add(new String[]{"Norwegian","no"});
        languages.add(new String[]{"Nyanja (Chichewa)","ny"});
        languages.add(new String[]{"Odia (Oriya)","or"});
        languages.add(new String[]{"Oromo","om"});
        languages.add(new String[]{"Pashto","ps"});
        languages.add(new String[]{"Persian","fa"});
        languages.add(new String[]{"Polish","pl"});
        languages.add(new String[]{"Portuguese (Portugal, Brazil)","pt"});
        languages.add(new String[]{"Punjabi","pa"});
        languages.add(new String[]{"Quechua","qu"});
        languages.add(new String[]{"Romanian","ro"});
        languages.add(new String[]{"Russian","ru"});
        languages.add(new String[]{"Samoan","sm"});
        languages.add(new String[]{"Sanskrit","sa"});
        languages.add(new String[]{"Scots Gaelic","gd"});
        languages.add(new String[]{"Sepedi","nso"});
        languages.add(new String[]{"Serbian","sr"});
        languages.add(new String[]{"Sesotho","st"});
        languages.add(new String[]{"Shona","sn"});
        languages.add(new String[]{"Sindhi","sd"});
        languages.add(new String[]{"Sinhala (Sinhalese)","si"});
        languages.add(new String[]{"Slovak","sk"});
        languages.add(new String[]{"Slovenian","sl"});
        languages.add(new String[]{"Somali","so"});
        languages.add(new String[]{"Spanish","es"});
        languages.add(new String[]{"Sundanese","su"});
        languages.add(new String[]{"Swahili","sw"});
        languages.add(new String[]{"Swedish","sv"});
        languages.add(new String[]{"Tagalog (Filipino)","tl"});
        languages.add(new String[]{"Tajik","tg"});
        languages.add(new String[]{"Tamil","ta"});
        languages.add(new String[]{"Tatar","tt"});
        languages.add(new String[]{"Telugu","te"});
        languages.add(new String[]{"Thai","th"});
        languages.add(new String[]{"Tigrinya","ti"});
        languages.add(new String[]{"Tsonga","ts"});
        languages.add(new String[]{"Turkish","tr"});
        languages.add(new String[]{"Turkmen","tk"});
        languages.add(new String[]{"Twi (Akan)","ak"});
        languages.add(new String[]{"Ukrainian","uk"});
        languages.add(new String[]{"Urdu","ur"});
        languages.add(new String[]{"Uyghur","ug"});
        languages.add(new String[]{"Uzbek","uz"});
        languages.add(new String[]{"Vietnamese","vi"});
        languages.add(new String[]{"Welsh","cy"});
        languages.add(new String[]{"Xhosa","xh"});
        languages.add(new String[]{"Yiddish","yi"});
        languages.add(new String[]{"Yoruba","yo"});
        languages.add(new String[]{"Zulu","zu"});
    }
}