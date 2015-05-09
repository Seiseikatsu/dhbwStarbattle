package com.starbattle.gameserver.weapon;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.battle.projectile.ProjectileEmitter;
import com.starbattle.gameserver.object.GameControl;
import com.starbattle.gameserver.player.GamePlayer;

public class WeaponInventar {

	private List<Weapon> weapons;
	private int selectedWeapon = 0;

	public WeaponInventar(GamePlayer player, GameControl gameControl) {
		weapons = new ArrayList<Weapon>();
		createStandardInventar(player, gameControl);

	}

	private void createStandardInventar(GamePlayer player, GameControl gameControl) {
		// add all weapons
		ProjectileEmitter emitter = gameControl.getProjectileEmitter();
		weapons.add(new PlasmaGun(player, gameControl, emitter));
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
		Weapon weapon = weapons.get(selectedWeapon);
		//if (weapon.canFire()) {
			weapon.fire();
		//}
	}

	public int getSelectedWeapon() {
		return selectedWeapon;
	}
}
