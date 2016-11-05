//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import jack.me.sonweather.sp.framework.AbstractPrefEditorField;
import jack.me.sonweather.sp.framework.EditorHelper;

public final class StringPrefEditorField<T extends EditorHelper<T>> extends AbstractPrefEditorField<T> {
    StringPrefEditorField(T editorHelper, String key) {
        super(editorHelper, key);
    }

    public T put(String value) {
        this.editorHelper.getEditor().putString(this.key, value);
        return this.editorHelper;
    }
}
