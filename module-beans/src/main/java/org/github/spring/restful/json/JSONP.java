package org.github.spring.restful.json;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.spring.enumeration.ContentType;
import org.github.spring.restful.JSON;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NonNull;

/**
 * JSON of jsonp.
 *
 * <pre>
 *   return JSONP.of();
 * </pre>
 *
 * @param <T> data
 * @author JYD_XL
 * @see java.util.function.Supplier
 * @see org.github.spring.restful.Returnable
 * @see org.github.spring.restful.JSON
 * @see org.github.spring.footstone.AbstractEntity
 * @see JSONBasic
 * @see JSONData
 */
@JsonIgnoreProperties("callback")
@SuppressWarnings("serial")
public class JSONP<T> extends JSONData<T> implements JSON {
    /** callback. */
    private String callback = CALL_BACK;

    /** Constructor. */
    public JSONP() {}

    /** Constructor. */
    public JSONP(T data) {
        this.withData(data);
    }

    /** Constructor. */
    public JSONP(String callback, T data) {
        this.withCallback(callback).withData(data);
    }

    @Override
    public void collect(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws IOException {
        Optional.of(callback).filter(CALL_BACK::equals).ifPresent(v -> this.setCallback(request.getParameter(CALL_BACK)));
        super.collect(request, response);
    }

    @Override
    public boolean functional() {
        return true;
    }

    @Override
    public String get() {
        return JOINER_EMPTY.join(callback, "(", super.get(), ")", ";");
    }

    @Override
    public ContentType contentType() {
        return ContentType.JSONP;
    }

    @Override
    public void release() {
        callback = CALL_BACK;
        super.release();
    }

    /** GET callback. */
    public String getCallback() {
        return callback;
    }

    /** SET callback. */
    public void setCallback(String callback) {
        Optional.ofNullable(callback).filter(v -> PATTERN_PARAM.matcher(v).matches()).ifPresent(v -> this.callback = v);
    }

    /** WITH callback. */
    public JSONP<T> withCallback(String callback) {
        this.setCallback(callback);
        return this;
    }

    /** Generator. */
    public static JSONP of() {
        return new JSONP();
    }

    /** Generator. */
    public static <V> JSONP<V> of(V data) {
        return new JSONP<>(data);
    }

    /** Generator. */
    @SafeVarargs
    public static <V> JSONP<V[]> of(V... data) {
        return new JSONP<>(data);
    }
}