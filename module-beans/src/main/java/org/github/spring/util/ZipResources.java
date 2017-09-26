package org.github.spring.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.core.io.Resource;

import com.google.common.io.ByteStreams;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

import lombok.NonNull;
import lombok.val;

public abstract class ZipResources {
  @CanIgnoreReturnValue
  public static OutputStream zip(@NonNull OutputStream outputStream, @NonNull Resource... resources) throws IOException {
    val zipOutputStream = new ZipOutputStream(outputStream);
    for (val resource : resources) {
      InputStream inputStream = resource.getInputStream();
      zipOutputStream.putNextEntry(new ZipEntry(resource.getFilename()));
      zipOutputStream.write(ByteStreams.toByteArray(inputStream));
      inputStream.close();
    }
    return zipOutputStream;
  }
}
