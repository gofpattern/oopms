/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
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
package openones.oopms.dms.validator;

import openones.oopms.dms.form.DefectForm;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Thach.Le
 */
@Component("AddDefectValidator")
public class BaseValidator implements Validator {
    /** Logger for logging. */
    final Logger log = Logger.getLogger(this.getClass());

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @return
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class<?> clazz) {
        return DefectForm.class.isAssignableFrom(clazz);
    }

    /**
     * [Explain the description for this method here].
     * @param arg0
     * @param arg1
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object target, Errors errors) {
        DefectForm bean = (DefectForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.defect.title");

        if (bean.getTitle().length() > 225 || bean.getTitle().length() < 10) {
            errors.rejectValue("title", "FieldLength.defect.title");
        }
    }

}
