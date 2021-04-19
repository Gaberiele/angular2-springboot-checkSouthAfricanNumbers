import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NumberService} from '../../service/number.service';
import {HttpClient} from '@angular/common/http';
import {PhoneNumber} from '../../model/phone-number';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  constructor(private http: HttpClient, private numberService: NumberService) {
  }

  showCorrect = false;
  showWrong = false;
  showFixed = false;
  selectedFile: File;
  partialList: Array<PhoneNumber>;
  showPartialList = false;
  showMessageChecked = false;
  messageNumberChecked = '';
  totalList: Array<PhoneNumber> = [];
  list: any;
  showList = false;
  headersPartial = ['ID', 'PHONE NUMBER', 'NOTE'];
  headers = ['ID', 'NUMBER'];
  formUpload: FormGroup;
  formCheck: FormGroup;
  wrongList = {};
  typeNumberChecked = '';


  ngOnInit(): void {
    this.formUpload = new FormGroup({
      file: new FormControl('', [Validators.required])
    });
    this.formCheck = new FormGroup({
      numberCheck: new FormControl('', [Validators.required])
    });
  }


  get fupload(): any {
    return this.formUpload.controls;
  }

  get fcheck(): any {
    return this.formCheck.controls;
  }

  showPartialNumbers(typeValidation: string): void {
    this.showPartialList = true;
    this.numberService.getPartialNumbers(typeValidation).subscribe(response => {
      if (response.payload != null && response.error == null) {
        this.partialList = response.payload;
        console.log(this.partialList);
      }
    });

    if (typeValidation === 'correct') {
      this.showCorrect = true;
      this.showFixed = false;
      this.showWrong = false;
    } else if (typeValidation === 'fixed') {
      this.showCorrect = false;
      this.showFixed = true;
      this.showWrong = false;
    } else if (typeValidation === 'wrong') {
      this.showCorrect = false;
      this.showFixed = false;
      this.showWrong = true;
    }
  }

  saveFileTemp(e): void {
    this.selectedFile = e.target.files[0];
  }

  uploadFile(): void {
    this.partialList = [];
    this.totalList = [];
    this.numberService.uploadFileCsv(this.selectedFile).subscribe(response => {
      if (response.payload != null && response.error == null) {
        this.totalList = response.payload;
        this.showList = true;
      }
    });
  }


  checkSingleNumber(): void {
    this.showMessageChecked = false;
    let numberToCheck: string;
    if (this.formCheck.controls.numberCheck.value) {
      numberToCheck = this.formCheck.controls.numberCheck.value;
      this.numberService.checkNumber(numberToCheck).subscribe(result => {
        if (result.error == null && result.payload != null) {
          const numberChecked: PhoneNumber = result.payload;
          this.messageNumberChecked = numberChecked.note;
          this.typeNumberChecked = numberChecked.tipoNum;
        }
      });
    } else {
      this.messageNumberChecked = 'REQUIRED';
    }
    this.showMessageChecked = true;
  }
}
