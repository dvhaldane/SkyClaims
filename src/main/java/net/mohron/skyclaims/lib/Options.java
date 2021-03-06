package net.mohron.skyclaims.lib;

import me.ryanhamshire.griefprevention.util.PlayerUtils;
import org.spongepowered.api.service.permission.Subject;

public enum Options {
	// The default radius of an Island upon creation
	BASE_RADIUS("skyclaims.baseradius", "32");

	private String name;
	private String value;

	Options(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public int getInt(Options option, Subject subject) {
		return PlayerUtils.getOptionIntValue(subject, option.name, Integer.parseInt(option.value));
	}
}
