package com.starbattle.gameserver.weapon;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.battle.projectile.ProjectileEmitter;
import com.starbattle.gameserver.object.GameControl;
import com.starbattle.gameserver.player.GamePlayer;

public class WeaponInventar {

	private List<Weapon> weapons;
	private int selectedWeapon = 0;
	private boolean disableWeapons = false;

	public WeaponInventar(GamePlayer player, GameControl gameControl) {
		weapons = new ArrayList<Weapon>();
		createStandardInventar(player, gameControl);

	}

	public void setDisableWeapons(boolean disableWeapons) {
		this.disableWeapons = disableWeapons;
	}

	private void createStandardInventar(GamePlayer player, GameControl gameControl) {
		// add all weapons
		ProjectileEmitter emitter = gameControl.getProjectileEmitter();
		weapons.add(new PlasmaGun(player, gameControl, emitter));
	}

	public void resetOnDeath() {
		// reset standardweapon ammo to full
		// drop every other weapon ammo
		weapons.get(0).reloadFull();
		for (int i = 1; i < weapons.size(); i++) {
			weapons.get(i).dropAmmo();
		}
	}

	public void update(float delta) {
		int anz = weapons.size();
		for (int i = 0; i < anz; i++) {
			weapons.get(i).update(delta);
		}
	}

	public void switchWeapon(boolean forward) {
		int select = selectedWeapon;
		int anz = weapons.size();

		// switch through all weapons and check if one is available
		for (int i = 0; i < anz; i++) {
			if (forward) {
				select++;
				if (select > anz) {
					select -= anz;
				}
			} else {
				select--;
				if (select < 0) {
					select = anz - 1;
				}
			}

			if (weapons.get(select).hasAmmo()) {
				selectedWeapon = select;
				return;
			}
		}

		// if no weapon was found with ammo, auto select standard weapon (on
		// place 0)
		selectedWeapon = 0;
	}

	// fires current selected weapon
	public void fireWeapon() {
		if (!disableWeapons) {
			Weapon weapon = weapons.get(selectedWeapon);
			if (weapon.canFire()) {
				weapon.fire();
			}
		}
	}

	public int getSelectedWeapon() {
		return selectedWeapon;
	}

	public int getCurrentAmmo() {
		Weapon weapon = weapons.get(selectedWeapon);
		return weapon.getAmmo();
	}
}
