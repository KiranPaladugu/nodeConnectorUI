package com.tcs.tools.config;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfigElement {
	private static enum TYPE {
		STRING, BOOLEAN, INT, DATE, BINARY
	};

	private String[] bool = { "yes", "no", "true", "false", "enable", "disable" };

	private String name;
	private Object value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
		convert(value);
	}

	private void convert(Object value) {
		if (value instanceof String) {
			if (checkIfboolConfig((String) value)) {
				if (("yes".equals(((String) value).trim().toLowerCase())
						|| ("enable".equals(((String) value).trim().toLowerCase())))) {
					this.value = new Boolean(true);
				} else
					this.value = Boolean.parseBoolean((String) value);
			}

			// String strVal = (String) value;
			// if (strVal.contains(",")) {
			// StringTokenizer tokenizer = new StringTokenizer(strVal, ",");
			// if (tokenizer.countTokens() > 0) {
			// List<String> list = new ArrayList<>();
			// while (tokenizer.hasMoreTokens()) {
			// list.add(tokenizer.nextToken());
			// }
			// this.value = list;
			// }
			// }

		}
	}

	public boolean checkIfboolConfig(String val) {
		for (String boolStr : bool) {
			if (boolStr.toLowerCase().equals(val.trim().toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public JComponent generateComponent() {

		if (value == null) {
			return null;
		}
		JComponent comp = null;
		if (value instanceof Boolean) {
			JCheckBox chkBox = new JCheckBox("", ((Boolean) value).booleanValue());
			comp = chkBox;
		} else if (value instanceof List<?>) {
			Vector<String> vect = new Vector<>();
			vect.addAll((Collection<? extends String>) value);
			JComboBox<String> combo = new JComboBox<>(new DefaultComboBoxModel<>(vect));
			combo.setEditable(true);
			comp = combo;
		} else if (value instanceof String) {
			Vector<String> vect = new Vector<>();
			vect.add((String) value);
			JComboBox<String> combo = new JComboBox<>(new DefaultComboBoxModel<>(vect));
			combo.setEditable(true);
			comp = combo;
		}
		if (comp != null) {
			JLabel lbl = new JLabel(name);
			lbl.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 2));
			panel.add(lbl);
			panel.add(comp);
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			comp = panel;
//			comp =LayoutUtils.arrangeComponantsInColoumn(true, GridBagConstraints.HORIZONTAL, new JLabel(name),comp).setExpandable(true).setExpandPolicy(ControlPanel.HORIZONTAL_FULL);
		}
		return comp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigElement other = (ConfigElement) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConfigElement [name=" + name + ", value=" + value + "]";
	}
}
