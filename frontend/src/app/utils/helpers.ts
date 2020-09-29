import { FormGroup, FormControl } from '@angular/forms';


export function loadData(group: FormGroup, object: any) {

  for (const propName in object) {

    if (object[propName] != null) {
      const control = group.get(propName);

      if (control != null) {
        control.setValue(object[propName]);
      }
    }
  }
}

export function passwordValidator(control: FormControl) {
  const password: string = control.value

  const LOWERCASE_REGEX = /[a-z]/
  const UPPERCASE_REGEX = /[A-Z]/
  const DIGIT_REGEX = /(\d)/

  const validations: boolean[] = []

  validations[0] = LOWERCASE_REGEX.test(password)
  validations[1] = UPPERCASE_REGEX.test(password)
  validations[2] = DIGIT_REGEX.test(password)

  return validations.includes(false) ? { passwordPattern: true } : null
}

export function compareValidator(_value1: string, _value2: string) {
  return (group: FormGroup) => {
    const value1Control = group.get(_value1)
    const value1: string = value1Control.value

    const value2Control = group.get(_value2)
    const value2: string = value2Control.value

    if (value1 !== value2) {
      value2Control.setErrors({ compare: true })
    } else {
      value2Control.setErrors(null)
    }
  }
}