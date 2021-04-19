import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {PhoneNumber} from '../model/phone-number';
import {Observable} from 'rxjs';
import {Response} from '../model/response';

@Injectable({
  providedIn: 'root'
})
export class NumberService {

  constructor(private http: HttpClient) {
  }

  getPartialNumbers(typeValidation: string): Observable<Response<Array<PhoneNumber>>> {
    let url = environment.baseUrl;
    url = url + '/phone-number/get-' + typeValidation;
    return this.http.get<Response<Array<PhoneNumber>>>(url);
  }


  uploadFileCsv(fileCsv: File): any {
    let url = environment.baseUrl;
    url = url + '/csv/upload';
    const formData: FormData = new FormData();
    formData.append('csvfile', fileCsv);
    return this.http.post<File>(url, formData);
  }

  checkNumber(numberPhone: string): Observable<Response<PhoneNumber>> {
    let url = environment.baseUrl;
    url = url + '/phone-number/check/' + numberPhone;
    return this.http.get<Response<PhoneNumber>>(url);
  }


}

