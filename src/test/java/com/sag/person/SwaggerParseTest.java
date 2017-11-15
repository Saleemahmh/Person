package com.sag.person;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import io.swagger.parser.util.SwaggerDeserializationResult;
import io.swagger.util.Json;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by SALEEM on 9/19/2017.
 */
public class SwaggerParseTest {

    @Test
    public void testSwagger() throws Exception{

            Swagger swagger = new SwaggerParser().read("./src/main/resources/swagger.json");
       // String swaggerString = Json.pretty(swagger);
            //System.out.print(swaggerString);
            //Assert.assertNotNull(swaggerString);
            Assert.assertEquals(swagger.getInfo().getTitle(),"Swagger Petstore");
        }
    }


