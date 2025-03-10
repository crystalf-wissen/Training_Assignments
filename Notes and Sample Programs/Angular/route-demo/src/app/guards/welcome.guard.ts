import { ActivatedRouteSnapshot, CanActivate, MaybeAsync, GuardResult, RouterStateSnapshot } from '@angular/router';
import { RoleService } from '../services/role.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class welcomeGuard implements CanActivate {
  constructor(private rs: RoleService) {}
  canActivate(route: ActivatedRouteSnapshot, state:RouterStateSnapshot): MaybeAsync<GuardResult> {
    if(route.data[0]==this.rs.role) {
      return true;
    } else {
      return false;
    }
  }
}