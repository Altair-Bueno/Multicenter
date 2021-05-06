package App.Multicenter.Platform;

import java.net.URL;

/**
 * Permite localizar elementos contenidos en el interior del JAR de forma sencilla.
 * Contiene constantes utilizadas para obtener la URL de los elementos contenidos
 * dentro del JAR
 */
public class InternalResourceLoader {
    // Constantes
    // TODO

    /**
     * Devuelve una URL al elemento solicitado. Tenga en cuenta que este método solo
     * sirve para localizar elementos en el interior de src, es decir, elementos
     * empaquetados con la aplicación. Un ejemplo de URL válida sería
     *
     * <b>App/Multicenter/Platform/Resources/ResourceExample.png</b>
     *
     * @param path Dirección del recurso en el interior del proyecto
     * @return URL al recurso
     */
    public static URL getResourceURL(String path){
        // TODO
        return null;
    }
}
