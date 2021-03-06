package io.adobe.cloudmanager;

/*-
 * #%L
 * Adobe Cloud Manager Client Library
 * %%
 * Copyright (C) 2020 Adobe Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.openssl.jcajce.JcaPKCS8Generator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdobeClientCredentialsTest {

    @Test
    public void testGetKeyFromPem() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        // Derived from: https://techxperiment.blogspot.com/2016/10/create-and-read-pkcs-8-format-private.html
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(2048);
        KeyPair keyPair = kpGen.generateKeyPair();
        PrivateKey expectedKey = keyPair.getPrivate();
        StringWriter sw = new StringWriter();
        try (JcaPEMWriter pw = new JcaPEMWriter(sw)) {
            pw.writeObject(new JcaPKCS8Generator(expectedKey, null).generate());
        }
        String pem = sw.toString();
        PrivateKey actualKey = AdobeClientCredentials.getKeyFromPem(pem);
        Assertions.assertEquals(expectedKey, actualKey);
    }
}
