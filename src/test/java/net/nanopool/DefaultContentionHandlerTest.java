/*
   Copyright 2008-2009 Christian Vest Hansen

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package net.nanopool;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;

public class DefaultContentionHandlerTest extends NanoPoolTestBase {

    @Override
    protected Configuration buildConfig() {
        return super.buildConfig().setPoolSize(1);
    }

    @Test
    public void testDefaultContentionHandler() throws SQLException {
        pool = npds();
        Connection con = pool.getConnection();
        assertNotNull(con);
        try {
            pool.getConnection();
            fail("Second getConnection() did not throw");
        } catch (SQLException sqle) {
            assertTrue(sqle.getMessage().contains("too contended"));
        }
    }
}
