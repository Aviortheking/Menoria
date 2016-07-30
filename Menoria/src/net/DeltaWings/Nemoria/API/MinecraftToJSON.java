package net.DeltaWings.Nemoria.API;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class MinecraftToJSON {

	public static String colors(String color){
		if(color.equalsIgnoreCase("0")) return "black";
		if(color.equalsIgnoreCase("1")) return "dark_blue";
		if(color.equalsIgnoreCase("2")) return "dark_green";
		if(color.equalsIgnoreCase("3")) return "dark_aqua";
		if(color.equalsIgnoreCase("4")) return "dark_red";
		if(color.equalsIgnoreCase("5")) return "dark_purple";
		if(color.equalsIgnoreCase("6")) return "gold";
		if(color.equalsIgnoreCase("7")) return "gray";
		if(color.equalsIgnoreCase("8")) return "dark_gray";
		if(color.equalsIgnoreCase("9")) return "blue";
		if(color.equalsIgnoreCase("a")) return "green";
		if(color.equalsIgnoreCase("b")) return "aqua";
		if(color.equalsIgnoreCase("c")) return "red";
		if(color.equalsIgnoreCase("d")) return "light_purple";
		if(color.equalsIgnoreCase("e")) return "yellow";
		if(color.equalsIgnoreCase("f")) return "white";
		return "none";
		}
	
	public static String special(String code){
		if(code.equalsIgnoreCase("k")) return "obfuscated";
		if(code.equalsIgnoreCase("l")) return "bold";
		if(code.equalsIgnoreCase("m")) return "strikethrough";
		if(code.equalsIgnoreCase("n")) return "underline";
		if(code.equalsIgnoreCase("o")) return "italic";
		if(code.equalsIgnoreCase("r")) return "reset";
		return "none";
	}
	
	public static String main(String string, Player player){
		//split de la phrase en lettres
		String[] parts = string.split("");
		//set result 
		List<String> result = new ArrayList<String>();;
		//set couleur de base
		String color = ",\"color\":\"white\"";
		//set specials de base
		String[] special = {
			",\"bold\":false",
			",\"italic\":false",
			",\"strikethrough\":false",
			",\"underlined\":false",
			",\"obfuscated\":false",
		};
		Boolean yolo = false;
		String one = "";
		/*
		 * for nombre de lettre de la phrase
		 * si yolo = true
		 * 	check si lettre est dans liste
		 * 	si oui
		 * 		on vire la lettre et on ferme le truc actuel
		 * 		check si c'est une couleur
		 * 		check si c'est un special
		 * 		on en reouvre un autre
		 * 	si non
		 * 		on remet le &
		 * 	set yolo = false
		 * si lettre actuel = &
		 * 	set yolo true
		 * sinon
		 *	ont les mets les une a cotés des autre
		 */
		for(int a=0;a<parts.length;a++){
			if( yolo == true){
				if(special(parts[a]) != "none" || colors(parts[a]) != "none"){
					result.add("{\"text\":\""+one+"\""+color+special[0]+special[1]+special[2]+special[3]+special[4]+"}");
					if(special(parts[a]) != "none" && player.hasPermission("delta.pi.color")){
						if(special(parts[a]) == "bold") special[0] = ",\"bold\":true";
						if(special(parts[a]) == "italic") special[1] = ",\"italic\":true";
						if(special(parts[a]) == "strikethrough") special[2] = ",\"strikethrough\":true";
						if(special(parts[a]) == "underlined") special[3] = ",\"underlined\":true";
						if(special(parts[a]) == "obfuscated") special[4] = ",\"obfuscated\":true";
						if(special(parts[a]) == "reset"){
							color = ",\"color\":\"white\"";
								special[0] = ",\"bold\":false";
								special[1] = ",\"italic\":false";
								special[2] = ",\"strikethrough\":false";
								special[3] = ",\"underlined\":false";
								special[4] = ",\"obfuscated\":false";
						}
					}else if(colors(parts[a]) != "none" && player.hasPermission("delta.pi.color")) color = ",\"color\":\""+colors(parts[a])+"\"";
					parts[a] = "";
					one = "";
				}else{
					one+= "&";
				}
				yolo = false;
			}
			if(parts[a].equals("&")){
				yolo = true;
			}else{
				if(parts[a].equals("\"")) one += "\\\"";
				else if(parts[a].equals("\'")) one += "\\\'";
				else one+= parts[a];

			}
		}
		if(one.length() != 0){
			result.add("{\"text\":\""+one+"\""+color+special[0]+special[1]+special[2]+special[3]+special[4]+"}");
			one = "";
		}
		String pcolor = "white";
		String perm = "delta.pi.prefix.color.";
		if(player.hasPermission(perm+"black")) pcolor = "black";
		else if(player.hasPermission(perm+"dark_blue")) pcolor = "dark_blue";
		else if(player.hasPermission(perm+"dark_green")) pcolor = "dark_green";
		else if(player.hasPermission(perm+"dark_aqua")) pcolor = "dark_aqua";
		else if(player.hasPermission(perm+"dark_red")) pcolor = "dark_red";
		else if(player.hasPermission(perm+"dark_purple")) pcolor = "dark_purple";
		else if(player.hasPermission(perm+"gold")) pcolor = "gold";
		else if(player.hasPermission(perm+"gray")) pcolor = "gray";
		else if(player.hasPermission(perm+"dark_gray")) pcolor = "dark_gray";
		else if(player.hasPermission(perm+"blue")) pcolor = "blue";
		else if(player.hasPermission(perm+"green")) pcolor = "green";
		else if(player.hasPermission(perm+"aqua")) pcolor = "aqua";
		else if(player.hasPermission(perm+"red")) pcolor = "red";
		else if(player.hasPermission(perm+"light_purple")) pcolor = "light_purple";
		else if(player.hasPermission(perm+"yellow")) pcolor = "yellow";
		else if(player.hasPermission(perm+"white")) pcolor = "white";
		
		
		
		
		
		//Changement de result en String avec un prefix et un suffix
		String end = "[\"\",{\"text\":\"[] \",\"bold\":true,\"color\":\"gold\"},{\"text\":\""+player.getName()+" \",\"color\":\""+pcolor+"\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/pi "+player.getName()+"\"}},{\"text\":\"> \",\"bold\":true,\"color\":\"gold\"},";
		for(int a = 0;a<result.size();a++){
			if(a != 0) end += ",";
			end += result.get(a);
		}
		end += "]";
		return end;
	}
}