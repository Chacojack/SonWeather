//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

public final class BooleanPrefEditorField<T extends EditorHelper<T>> extends AbstractPrefEditorField<T> {
    BooleanPrefEditorField(T editorHelper, String key) {
        super(editorHelper, key);
    }

    public T put(boolean value) {
        this.editorHelper.getEditor().putBoolean(this.key, value);
        return this.editorHelper;
    }
}
