package me.notaeris.cobra.util;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;

    /**
     * constructor
     *
     * @param material item material
     * @param amount item amount
     */
    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
    }

    /**
     * constructor
     *
     * @param material item material
     */
    public ItemBuilder(Material material) {
        this(material, 1);
    }

    /**
     * constructor
     *
     * @param material item material
     * @param amount item amount
     * @param value item value
     */
    public ItemBuilder(Material material, int amount, short value) {
        itemStack = new ItemStack(material, amount, value);
    }

    /**
     * set the name of a item
     *
     * @param name item name
     * @return the item
     */
    public ItemBuilder setName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * set the lore of a item
     *
     * @param lore item lore
     * @return the item
     */
    public ItemBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * add a enchant to a item
     *
     * @param enchantment add a enchant
     * @param level add a level
     * @return the item
     */
    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(enchantment, level, true);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * set the color of a item
     *
     * @param color item color
     * @return the item
     */
    public ItemBuilder setColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)itemStack.getItemMeta();
        leatherArmorMeta.setColor(color);
        itemStack.setItemMeta(leatherArmorMeta);
        return this;
    }

    /**
     * create a itemstack
     *
     * @return the itemstack
     */
    public ItemStack create() {
        return itemStack;
    }
}
