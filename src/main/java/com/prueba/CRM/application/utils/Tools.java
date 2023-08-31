package com.prueba.CRM.application.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class Tools {

    private Tools() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Metodo encargado de castear el formato de la fecha
     * @param localDateTime LocalDateTime
     * @param formatLocalDateTime String
     * @return String
     */
    public static String localDateTimeToString(LocalDateTime localDateTime, String formatLocalDateTime){
        String result = "";
        if (localDateTime!=null){
            result = localDateTime.format(DateTimeFormatter.ofPattern(formatLocalDateTime));
        }
        return result;
    }

    /**
     * Metodo encargado de castear el formato de la fecha
     * @param dateTimeString String
     * @param formatLocalDateTime String
     * @return LocalDateTime
     */
    public static LocalDateTime stringToLocalDateTime(String dateTimeString, String formatLocalDateTime){
        if (dateTimeString==null || dateTimeString.equals("")){
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatLocalDateTime);
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Metodo encargado de castear el formato de la fecha
     * @param localDate LocalDate
     * @param formatLocalDate String
     * @return String
     */
    public static String localDateToString(LocalDate localDate, String formatLocalDate){
        String result = "";
        if (localDate!=null){
            result = localDate.format(DateTimeFormatter.ofPattern(formatLocalDate));
        }
        return result;
    }

    /**
     * Metodo encargado de castear el formato de la fecha
     * @param dateString String
     * @param formatLocalDate String
     * @return LocalDate
     */
    public static LocalDate stringToLocalDate(String dateString, String formatLocalDate){
        if (dateString==null || dateString.equals("")){
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatLocalDate);
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Metodo encargado de codificar el adjunto a base64
     * @param multipartFile MultipartFile
     * @return String
     */
    public static String codificarBase64(MultipartFile multipartFile) {
        InputStream inputStream = null;
        String encodeFile = "";
        try {
            byte[] contenido = multipartFile.getBytes();
            inputStream = new BufferedInputStream(multipartFile.getInputStream());
            inputStream.read(contenido);
            encodeFile = Base64.getEncoder().encodeToString(contenido);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return encodeFile;
    }

    /**
     * Metodo encargado de decodificar base64 a arreglo de byte
     * @param dataBase64 String
     * @return byte[]
     */
    public static byte[] decodificarBase64(String dataBase64){
        byte[] contenido = null;
        contenido = Base64.getDecoder().decode(dataBase64);
        return contenido;
    }

    /**
     * Metodo encargado de generar el .zip
     * Enviamos un Mapa con la clave siendo el nombre del archivo
     *"archivo.txt" y en el valor los byte del archivo.
     * @param mapOfFile Map<String, byte[]>
     * @return byte[]
     */
    public static byte[] filesToZip(Map<String, byte[]> mapOfFile) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Utilizamos try-with-resources para que java se encargue de cerrar
        // el recurso ZipOutputStream zos al finalizar
        try (ZipOutputStream zos = new ZipOutputStream(baos);) {
            for (Map.Entry<String, byte[]> entry : mapOfFile.entrySet()) {
                // Creamos una entrada por cada archivo
                ZipEntry zipEntr = new ZipEntry(entry.getKey());
                byte[] input = entry.getValue();
                zipEntr.setSize(input.length);
                zos.putNextEntry(zipEntr);
                zos.write(input);
                // Cerramos la entrada pero no la variable zos
                zos.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
