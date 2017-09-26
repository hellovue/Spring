package org.github.spring.footstone;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.function.Supplier;

/**
 * NaiveProperties.
 *
 * @author JYD_XL
 */
@SuppressWarnings("serial")
public class NaiveProperties extends Properties {
  /** Constructor. */
  public NaiveProperties() {}

  /**
   * Constructor.
   *
   * @param properties Properties
   */
  public NaiveProperties(Properties properties) {
    super(properties);
  }

  /**
   * Constructor.
   *
   * @param loader   ResourceLoader
   * @param location String
   * @throws IOException Exception
   */
  public NaiveProperties(Supplier<ResourceLoader> loader, String location) throws IOException {
    this.load(loader.get().getResource(location).getInputStream());
  }

  /**
   * Constructor.
   *
   * @param location String
   * @throws IOException Exception
   */
  public NaiveProperties(String location) throws IOException {
    this(PathMatchingResourcePatternResolver::new, location);
  }

  /**
   * Constructor.
   *
   * @param resource Resource
   */
  public NaiveProperties(Resource resource) throws IOException {
    this.load(resource.getInputStream());
  }

  /**
   * Constructor.
   *
   * @param inputStream InputStream
   */
  public NaiveProperties(InputStream inputStream) throws IOException {
    this.load(inputStream);
  }

  /**
   * Generator.
   *
   * @param resource Resource
   * @return NaiveProperties
   * @throws IOException Exception
   */
  public static NaiveProperties of(Resource resource) throws IOException {
    return new NaiveProperties((resource));
  }
}
