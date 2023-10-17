import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarComponentComponent } from './nav-bar-component.component';

describe('NavBarComponentComponent', () => {
  let component: NavBarComponentComponent;
  let fixture: ComponentFixture<NavBarComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavBarComponentComponent]
    });
    fixture = TestBed.createComponent(NavBarComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
