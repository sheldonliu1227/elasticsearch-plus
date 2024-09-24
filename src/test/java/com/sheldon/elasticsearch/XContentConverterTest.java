package com.sheldon.elasticsearch;


import com.sheldon.elasticsearch.core.XContentConverter;
import com.sheldon.elasticsearch.core.exception.ClassInaccuracyException;
import org.elasticsearch.common.Strings;
import org.elasticsearch.xcontent.XContentBuilder;

import java.io.IOException;

public class XContentConverterTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            TestDocument testDocument = TestDocument.class.newInstance();
            XContentBuilder builder = XContentConverter.object2Builder(testDocument);
            System.out.println(Strings.toString(builder));
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassInaccuracyException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
