package com.sheldon.elasticsearch;


import com.sheldon.elasticsearch.plus.core.xcontent.ClassParser;
import org.elasticsearch.common.Strings;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

class XContentConverterTest {

    @Test
    public void test() {
        try {
            TestDocument testDocument = TestDocument.class.newInstance();
            ClassParser classParser = new ClassParser(testDocument.getClass());
            XContentBuilder builder = classParser.parse(XContentFactory.jsonBuilder().startObject());
            builder.endObject();
            System.out.println(Strings.toString(builder));
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
