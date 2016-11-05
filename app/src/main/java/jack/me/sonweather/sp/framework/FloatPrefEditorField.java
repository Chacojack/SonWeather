//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

public final class FloatPrefEditorField<T extends EditorHelper<T>> extends AbstractPrefEditorField<T> {
    FloatPrefEditorField(T editorHelper, String key) {
        super(editorHelper, key);
    }

    public T put(float value) {
        this.editorHelper.getEditor().putFloat(this.key, value);
        return this.editorHelper;
    }
}
