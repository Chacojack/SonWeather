//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

public final class IntPrefEditorField<T extends EditorHelper<T>> extends AbstractPrefEditorField<T> {
    IntPrefEditorField(T editorHelper, String key) {
        super(editorHelper, key);
    }

    public T put(int value) {
        this.editorHelper.getEditor().putInt(this.key, value);
        return this.editorHelper;
    }
}
