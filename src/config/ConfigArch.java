package config;

public enum ConfigArch {
   PROPERTIE("resources/propertie.txt"),
   RUTA_ARCHIVOS("resources/"),
   ARCHIVO_EN_CLARO("resources/archivo.txt");
   private final String ruta;

   ConfigArch(String ruta) {
      this.ruta = ruta;
   }

   public String getRuta() {
      return ruta;
   }

   @Override
   public String toString() {
      return "Config{" +
              "ruta='" + ruta + '\'' +
              '}';
   }
}
