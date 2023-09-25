import { TestBed } from '@angular/core/testing';

import { CheckuotService } from './checkuot.service';

describe('CheckuotService', () => {
  let service: CheckuotService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckuotService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
