package io.yawp.commons.utils;

import io.yawp.commons.http.HttpException;
import io.yawp.commons.http.RequestContext;
import io.yawp.repository.models.ObjectHolder;
import io.yawp.repository.Repository;
import io.yawp.servlet.EndpointServlet;
import org.junit.Before;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServletTestCase extends EndpointTestCase {

    private static EndpointServlet servlet;

    @Before
    public void createServlet() {
        if (servlet != null) {
            return;
        }

        servlet = new EndpointServlet("io.yawp") {

            private static final long serialVersionUID = 3374113392343671861L;

            @Override
            protected Repository getRepository(RequestContext ctx) {
                return yawp.setRequestContext(ctx);
            }

        };
    }

    private EndpointServlet servlet() {
        return servlet;
    }

    // get

    protected String get(String uri, String json, Map<String, String> params) {
        return servlet().execute(ctx("GET", uri, json, params)).getText();
    }

    protected String get(String uri) {
        return get(uri, null, new HashMap<>());
    }

    protected String get(String uri, String json) {
        return get(uri, json, new HashMap<>());
    }

    protected String get(String uri, Map<String, String> params) {
        return get(uri, null, params);
    }

    protected void assertGetWithStatus(String uri, String json, Map<String, String> params, int status) {
        try {
            get(uri, json, params);
        } catch (HttpException e) {
            assertEquals(status, e.getHttpStatus());
            return;
        }
        assertEquals(200, status);
    }

    protected void assertGetWithStatus(String uri, int status) {
        assertGetWithStatus(uri, null, new HashMap<>(), status);
    }

    protected void assertGetWithStatus(String uri, String json, int status) {
        assertGetWithStatus(uri, json, new HashMap<>(), status);
    }

    protected void assertGetWithStatus(String uri, Map<String, String> params, int status) {
        assertGetWithStatus(uri, null, params, status);
    }

    // post

    protected String post(String uri, String json, Map<String, String> params) {
        return servlet().execute(ctx("POST", uri, json, params)).getText();
    }

    protected String post(String uri) {
        return post(uri, null, new HashMap<>());
    }

    protected String post(String uri, String json) {
        return post(uri, json, new HashMap<>());
    }

    protected String post(String uri, Map<String, String> params) {
        return post(uri, null, params);
    }

    protected void assertPostWithStatus(String uri, String json, Map<String, String> params, int status) {
        try {
            post(uri, json, params);
        } catch (HttpException e) {
            assertEquals(status, e.getHttpStatus());
            return;
        }
        assertEquals(200, status);
    }

    protected void assertPostWithStatus(String uri, int status) {
        assertPostWithStatus(uri, null, new HashMap<>(), status);
    }

    protected void assertPostWithStatus(String uri, String json, int status) {
        assertPostWithStatus(uri, json, new HashMap<>(), status);
    }

    protected void assertPostWithStatus(String uri, Map<String, String> params, int status) {
        assertPostWithStatus(uri, null, params, status);
    }

    // put

    protected String put(String uri, String json, Map<String, String> params) {
        return servlet().execute(ctx("PUT", uri, json, params)).getText();
    }

    protected String put(String uri) {
        return put(uri, null, new HashMap<>());
    }

    protected String put(String uri, String json) {
        return put(uri, json, new HashMap<>());
    }

    protected String put(String uri, Map<String, String> params) {
        return put(uri, null, params);
    }

    protected void assertPutWithStatus(String uri, String json, Map<String, String> params, int status) {
        try {
            put(uri, json, params);
        } catch (HttpException e) {
            assertEquals(status, e.getHttpStatus());
            return;
        }
        assertEquals(200, status);
    }

    protected void assertPutWithStatus(String uri, int status) {
        assertPutWithStatus(uri, null, new HashMap<>(), status);
    }

    protected void assertPutWithStatus(String uri, String json, int status) {
        assertPutWithStatus(uri, json, new HashMap<>(), status);
    }

    protected void assertPutWithStatus(String uri, Map<String, String> params, int status) {
        assertPutWithStatus(uri, null, params, status);
    }

    // patch

    protected String patch(String uri, String json, Map<String, String> params) {
        return servlet().execute(ctx("PATCH", uri, json, params)).getText();
    }

    protected String patch(String uri) {
        return patch(uri, null, new HashMap<>());
    }

    protected String patch(String uri, String json) {
        return patch(uri, json, new HashMap<>());
    }

    protected String patch(String uri, Map<String, String> params) {
        return patch(uri, null, params);
    }

    protected void assertPatchWithStatus(String uri, String json, Map<String, String> params, int status) {
        try {
            patch(uri, json, params);
        } catch (HttpException e) {
            assertEquals(status, e.getHttpStatus());
            return;
        }
        assertEquals(200, status);
    }

    protected void assertPatchWithStatus(String uri, int status) {
        assertPatchWithStatus(uri, null, new HashMap<>(), status);
    }

    protected void assertPatchWithStatus(String uri, String json, int status) {
        assertPatchWithStatus(uri, json, new HashMap<>(), status);
    }

    protected void assertPatchWithStatus(String uri, Map<String, String> params, int status) {
        assertPatchWithStatus(uri, null, params, status);
    }

    // delete

    protected String delete(String uri, String json, Map<String, String> params) {
        return servlet().execute(ctx("DELETE", uri, json, params)).getText();
    }

    protected String delete(String uri) {
        return delete(uri, null, new HashMap<>());
    }

    protected String delete(String uri, String json) {
        return delete(uri, json, new HashMap<>());
    }

    protected String delete(String uri, Map<String, String> params) {
        return delete(uri, null, params);
    }

    protected void assertDeleteWithStatus(String uri, String json, Map<String, String> params, int status) {
        try {
            delete(uri, json, params);
        } catch (HttpException e) {
            assertEquals(status, e.getHttpStatus());
            return;
        }
        assertEquals(200, status);
    }

    protected void assertDeleteWithStatus(String uri, int status) {
        assertDeleteWithStatus(uri, null, new HashMap<>(), status);
    }

    protected void assertDeleteWithStatus(String uri, String json, int status) {
        assertDeleteWithStatus(uri, json, new HashMap<>(), status);
    }

    protected void assertDeleteWithStatus(String uri, Map<String, String> params, int status) {
        assertDeleteWithStatus(uri, null, params, status);
    }

    // helpers

    protected RequestContext ctx(String method, String uri) {
        return new RequestContextMock.Builder().method(method).uri(uri).build();
    }

    protected RequestContext ctx(String method, String uri, String json) {
        return new RequestContextMock.Builder().method(method).uri(uri).json(json).build();
    }

    protected RequestContext ctx(String method, String uri, Map<String, String> params) {
        return new RequestContextMock.Builder().method(method).uri(uri).params(params).build();
    }

    protected RequestContext ctx(String method, String uri, String json, Map<String, String> params) {
        return new RequestContextMock.Builder().method(method).uri(uri).json(json).params(params).build();
    }

    protected String parseIds(String format, Object... objects) {
        List<String> longIds = new ArrayList<>();

        for (Object object : objects) {
            ObjectHolder objectHolder = new ObjectHolder(object);
            longIds.add(String.valueOf(objectHolder.getId().getSimpleValue()));
        }

        return String.format(format, longIds.toArray());
    }

    protected String uri(String uriFormat, Object... objects) {
        return parseIds(uriFormat, objects);
    }

    protected String json(String uriFormat, Object... objects) {
        return parseIds(uriFormat, objects);
    }

    protected Map<String, String> params(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
