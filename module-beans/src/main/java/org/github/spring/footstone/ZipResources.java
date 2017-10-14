package org.github.spring.footstone;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import lombok.NonNull;
import lombok.val;

import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResource;

import com.google.common.io.ByteStreams;

public abstract class ZipResources {
  public static void zipServletContextResources(@NonNull OutputStream outputStream, @NonNull Resource... resources) throws IOException {
    val zipOutputStream = new ZipOutputStream(outputStream);
    for (val res : resources) {
      ServletContextResource resource = (ServletContextResource) res;
      if (!resource.isReadable()) {continue;}
      InputStream inputStream = resource.getInputStream();
      zipOutputStream.putNextEntry(new ZipEntry(resource.getPath().substring(1)));
      ByteStreams.copy(inputStream, zipOutputStream);
      inputStream.close();
    }
    zipOutputStream.close();
  }
}
