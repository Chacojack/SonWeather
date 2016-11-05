//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public final class SetXmlSerializer {
    private static final String NAMESPACE = "";
    private static final String STRING_TAG = "AA_string";
    private static final String SET_TAG = "AA_set";

    private SetXmlSerializer() {
    }

    public static String serialize(Set<String> set) {
        if(set == null) {
            set = Collections.emptySet();
        }

        StringWriter writer = new StringWriter();
        XmlSerializer serializer = Xml.newSerializer();

        try {
            serializer.setOutput(writer);
            serializer.startTag("", "AA_set");
            Iterator e = set.iterator();

            while(e.hasNext()) {
                String string = (String)e.next();
                serializer.startTag("", "AA_string").text(string).endTag("", "AA_string");
            }

            serializer.endTag("", "AA_set").endDocument();
        } catch (IllegalArgumentException var5) {
            ;
        } catch (IllegalStateException var6) {
            ;
        } catch (IOException var7) {
            ;
        }

        return writer.toString();
    }

    public static Set<String> deserialize(String data) {
        TreeSet stringSet = new TreeSet();
        XmlPullParser parser = Xml.newPullParser();

        try {
            parser.setInput(new StringReader(data));
            parser.next();
            parser.require(2, "", "AA_set");

            while(parser.next() != 3) {
                parser.require(2, "", "AA_string");
                parser.next();
                parser.require(4, (String)null, (String)null);
                stringSet.add(parser.getText());
                parser.next();
                parser.require(3, (String)null, "AA_string");
            }

            return stringSet;
        } catch (XmlPullParserException var4) {
            Log.w("getStringSet", var4);
            return null;
        } catch (IOException var5) {
            Log.w("getStringSet", var5);
            return null;
        }
    }
}
