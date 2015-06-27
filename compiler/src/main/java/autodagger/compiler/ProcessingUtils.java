package autodagger.compiler;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * @author Lukasz Piliszczuk - lukasz.pili@gmail.com
 */
final class ProcessingUtils {

    /**
     * Types.isSameType() does not work when the origin element that triggers annotation
     * processing, and calls Types.isSameType() is generated by an other annotation processor
     * Workaround is to compare the full qualified names of the two types
     */
    public static boolean areTypesEqual(TypeElement typeElement1, TypeElement typeElement2) {
        return typeElement1.getQualifiedName().equals(typeElement2.getQualifiedName());
    }

    public static boolean areTypesEqual(TypeMirror typeMirror1, TypeMirror typeMirror2) {
        TypeElement typeElement1 = MoreElements.asType(MoreTypes.asElement(typeMirror1));
        TypeElement typeElement2 = MoreElements.asType(MoreTypes.asElement(typeMirror2));

        return typeElement1.getQualifiedName().equals(typeElement2.getQualifiedName());
    }


    /**
     * See compareTypeElements() doc for explanation of workaround
     *
     * @return If one type equals return true, false otherwise
     */
    public static boolean compareTypeWithOneOfSeverals(TypeMirror typeMirror, TypeMirror... severals) {
        Element element = MoreTypes.asElement(typeMirror);
        TypeElement typeElement = MoreElements.asType(element);

        for (TypeMirror tm : severals) {
            Element e = MoreTypes.asElement(tm);
            TypeElement te = MoreElements.asType(e);

            if (typeElement.getQualifiedName().equals(te.getQualifiedName())) {
                return true;
            }
        }

        return false;
    }


}