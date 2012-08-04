/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package  org.jasig.portal.security.provider;

import org.jasig.portal.security.ISecurityContext;
import org.jasig.portal.security.ISecurityContextFactory;


/**
 * <p>The factory class for the cache LDAP security context. 
 * Just returns a new instance of the CacheLdapSecurityContext.</p>
 *
 * @author Russell Tokuyama (University of Hawaii)
 * @author Ken Weiner, kweiner@unicon.net
 * @version $Revision$
 * @deprecated As of uPortal 2.1.3, use {@link SimpleLdapSecurityContextFactory} chained with {@link CacheSecurityContextFactory} instead
 */
public class CacheLdapSecurityContextFactory implements ISecurityContextFactory {

  /**
   * Returns a new CacheLdapSecurityContext
   * @return a new CacheLdapSecurityContext
   */
  public ISecurityContext getSecurityContext () {
    return new CacheLdapSecurityContext();
  }
}



