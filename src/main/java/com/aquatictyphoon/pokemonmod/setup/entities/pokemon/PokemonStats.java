package com.aquatictyphoon.pokemonmod.setup.entities.pokemon;

import net.minecraft.commands.arguments.MessageArgument;

import javax.annotation.Nonnull;

@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class PokemonStats {

    public enum ShinyOptions {
        NORMAL,
        SHINY,
        SPECIAL,
    }
    public enum PokemonTypes {
        NONE,
        NORMAL,
        FIRE,
        FIGHTING,
        WATER,
        FLYING,
        GRASS,
        POISON,
        ELECTRIC,
        GROUND,
        PSYCHIC,
        ROCK,
        ICE,
        BUG,
        DRAGON,
        GHOST,
        DARK,
        STEEL,
        FAIRY,
        SHADOW
    }

    public enum Species {
        BADEGG("Bad Egg",45,45,45,45,45,45, PokemonTypes.NONE,  PokemonTypes.NONE, 45),
        BULBASAUR("Bulbasaur",45,	49,	49,	65,	65,	45, PokemonTypes.GRASS, PokemonTypes.POISON, 45),
        IVYSAUR("Ivysaur",60,	62,	63,	80,	80,	60, PokemonTypes.GRASS, PokemonTypes.POISON, 45),
        VENUSAUR("Venusaur",80	,82	,83	,100	,100	,80, PokemonTypes.GRASS, PokemonTypes.POISON, 45),
        CHARMANDER("Charmander",39	,52	,43	,60	,50	,65, PokemonTypes.FIRE, PokemonTypes.NONE, 45),
        CHARMELEON("Charmeleon",58,	64	,58	,80	,65	,80, PokemonTypes.FIRE, PokemonTypes.NONE, 45),
        CHARIZARD("Charizard",78,	84,	78,	109,	85,	100, PokemonTypes.FIRE, PokemonTypes.FLYING, 45),
        SQUIRTLE("Squirtle",44,	48,	65,	50,	64,	43, PokemonTypes.WATER, PokemonTypes.NONE, 45),
        WARTORTLE("Wartortle",59,	63,	80,	65,	80,	58, PokemonTypes.WATER, PokemonTypes.NONE, 45),
        BLASTOISE("Blastoise",79,	83	,100,	85,	105	,78, PokemonTypes.WATER, PokemonTypes.NONE, 45),
        CATERPIE("Caterpie",45,	30,	35,	20,	20,	45, PokemonTypes.BUG, PokemonTypes.NONE, 255),
        METAPOD("Metapod",50	,20,	55,	25,	25	,30, PokemonTypes.BUG, PokemonTypes.NONE, 120),
        BUTTERFREE("Butterfree",60	,45,	50,	90,	80	,70, PokemonTypes.BUG, PokemonTypes.FLYING, 45),
        WEEDLE("Weedle",40,	35	,30	,20,	20,	50 ,PokemonTypes.BUG, PokemonTypes.POISON, 255),
        KAKUNA("Kakuna",45	,25,	50,	25,	25,	35, PokemonTypes.BUG, PokemonTypes.POISON, 120),
        BEEDRILL("Beedrill",65,	90,	40	,45,	80	,75, PokemonTypes.BUG, PokemonTypes.POISON, 45),
        PIDGEY("Pidgey",40,	45	,40,	35,	35,	56, PokemonTypes.FLYING, PokemonTypes.NORMAL,255),
        PIDGEOTTO("Pidgeotto",63,	60,	55,	50,	50,	71, PokemonTypes.FLYING, PokemonTypes.NORMAL,120),
        PIDGEOT("Pidgeot",83,	80,	75,	70,	70,	101, PokemonTypes.FLYING, PokemonTypes.NORMAL,45),
        RATTATA("Rattata",30,	56	,35	,25,	35,	72, PokemonTypes.NORMAL, PokemonTypes.NONE,255),
        RATICATE("Raticate",55,	81,	60,	50,	70,	97, PokemonTypes.NORMAL, PokemonTypes.NONE,127),
        SPEAROW("Spearow",40	,60,	30	,31,	31,	70, PokemonTypes.FLYING, PokemonTypes.NORMAL,255),
        FEAROW("Fearow",65	,90,	65	,61,	61,	100, PokemonTypes.FLYING, PokemonTypes.NORMAL,90),
        EKANS("Ekans",35	,60,	44	,40,	54,	55, PokemonTypes.POISON, PokemonTypes.NONE,255),
        ARBOK("Arbok",60	,95,	69	,65,	79,	80,  PokemonTypes.POISON, PokemonTypes.NONE,90),
        PIKACHU("Pikachu",35	,55,	40	,50,	50,	90, PokemonTypes.ELECTRIC, PokemonTypes.NONE,190),
        RAICHU("Raichu",60	,90,	55	,90,	80,	110, PokemonTypes.ELECTRIC, PokemonTypes.NORMAL,75),
        SANDSHREW("Sandshrew",50,	75,	85,	20,	30,	40, PokemonTypes.GROUND, PokemonTypes.NONE,255),
        SANDSLASH("Sandslash",75,	100,	110	,45,	55	,65, PokemonTypes.GROUND, PokemonTypes.NONE,90),
        NIDORAN_F("Nidoran ♀",55,	47,	52	,40,	40	,41, PokemonTypes.POISON, PokemonTypes.NONE,235),
        NIDORINA("Nidorina",70	,62	,67	,55	,55	,56,PokemonTypes.POISON, PokemonTypes.NONE,120),
        NIDOQUEEN("Nidoqueen",90,	92,	87,	75,	85,	76,PokemonTypes.POISON, PokemonTypes.NONE,45),
        NIDORAN_M("Nidoran ♂",46,	57,	40,	40,	40	,50,PokemonTypes.POISON, PokemonTypes.NONE,235),
        NIDORINO("Nidrorino",61	,72	,57	,55,	55	,65,PokemonTypes.POISON, PokemonTypes.NONE,120),
        NIDOKING("Nidoking",81	,102,	77	,85,	75	,85,PokemonTypes.POISON, PokemonTypes.NONE,45),
        CLEFAIRY("Clefairy",70	,45,	48	,60,	65	,35, PokemonTypes.FAIRY, PokemonTypes.NONE,150),
        CLEFABLE("Clefable",95	,70	,73	,95	,90	,60, PokemonTypes.FAIRY, PokemonTypes.NONE,25),
        VULPIX("Vulpix",38	,41	,40	,50	,65	,65, PokemonTypes.FIRE, PokemonTypes.NONE,190),
        NINETALES("Ninetales",73	,76,	75,	81	,100,	100, PokemonTypes.FIRE, PokemonTypes.NONE,75),
        JIGGLYPUFF("Jigglypuff",115,	45,	20,	45	,25,	20, PokemonTypes.NORMAL, PokemonTypes.FAIRY,170),
        WIGGLYTUFF("Wigglytuff",140,	70,	45,	85	,50,	45, PokemonTypes.NORMAL, PokemonTypes.FAIRY,50),
        ZUBAT("Zubat",40	,45	,35	,30	,40	,55, PokemonTypes.POISON, PokemonTypes.FLYING,255),
        GOLBAT("Golbat",75	,80	,70	,65	,75	,90, PokemonTypes.POISON, PokemonTypes.FLYING,90),
        ODDISH("Oddish",45	,50	,55	,75	,65	,30, PokemonTypes.GRASS, PokemonTypes.POISON,255),
        GLOOM("Gloom",60	,65	,70	,85	,75	,40, PokemonTypes.GRASS, PokemonTypes.POISON,120),
        VILEPLUME("Vileplume",75,	80,	85	,110,	90,	50, PokemonTypes.GRASS, PokemonTypes.POISON,45),
        PARAS("Paras",35	,70	,55,	45	,55,	25, PokemonTypes.BUG, PokemonTypes.GRASS,190),
        PARASECT("Parasect",60	,95	,80,	60	,80,	30, PokemonTypes.BUG, PokemonTypes.GRASS,100),
        VENONAT("Venonat",60	,55	,50	,40	,55,	45, PokemonTypes.BUG, PokemonTypes.POISON,75),
        VENOMOTH("Venomoth",70	,65	,60	,90	,75	,90, PokemonTypes.BUG, PokemonTypes.POISON,190),
        DIGLETT("Diglette",10	,55	,25	,35	,45	,95, PokemonTypes.GROUND, PokemonTypes.NONE,255),
        DUGTRIO("Dugrtrio",35	,100	,50	,50	,70	,120, PokemonTypes.GROUND, PokemonTypes.NONE,50),
        MEOWTH("Meowth",40	,45	,35	,40	,40	,90, PokemonTypes.NORMAL, PokemonTypes.NONE,255),
        PERSIAN("Persian",65	,70	,60	,65	,65	,115, PokemonTypes.NORMAL, PokemonTypes.NONE,90),
        PSYDUCK("Psyduck",50	,52	,48	,65	,50	,55, PokemonTypes.WATER, PokemonTypes.NONE,190),
        GOLDUCK("Golduck",80	,82	,78	,95	,80	,85, PokemonTypes.WATER, PokemonTypes.NONE,75),
        MANKEY("Mankey",40	,80	,35	,35	,45	,70, PokemonTypes.FIGHTING, PokemonTypes.NONE,190),
        PRIMEAPE("Primape",65	,105	,60	,60	,70	,95, PokemonTypes.FIGHTING, PokemonTypes.NONE,75),
        GROWLITHE("Growlthe",55,	70,	45,	70,	50,	60, PokemonTypes.FIRE, PokemonTypes.NONE,190),
        ARCANINE("Arcanine",90	,110	,80	,100	,80	,95, PokemonTypes.FIRE, PokemonTypes.NONE,75),
        POLIWAG("Poliwag",40	,50	,40	,40	,40	,90, PokemonTypes.WATER, PokemonTypes.NONE,255),
        POLIWHIRL("Poliwhirl",65,	65,	65,	50	,50,	90, PokemonTypes.WATER, PokemonTypes.NONE,120),
        POLIWRATH("Poliwrath",90,	95,	95,	70	,90	,70, PokemonTypes.WATER, PokemonTypes.FIGHTING,46),
        ABRA("Abra",25	,20	,15	,105	,55	,90 , PokemonTypes.PSYCHIC, PokemonTypes.NONE,200),
        KADABRA("Kadabra",40,	35	,30	,120	,70,	105, PokemonTypes.PSYCHIC, PokemonTypes.NONE,100),
        ALAKAZAM("Alakazam",55,	50,	45	,135,	95,	120, PokemonTypes.PSYCHIC, PokemonTypes.NONE,50),
        MACHOP("Machop",70	,80	,50	,35	,35	,35, PokemonTypes.FIGHTING, PokemonTypes.NONE,180),
        MACHOKE("Machoke",80	,100	,70	,50	,60	,45,  PokemonTypes.FIGHTING, PokemonTypes.NONE,90),
        MACHAMP("Machamp",90	,130	,80	,65	,85,	55,  PokemonTypes.FIGHTING, PokemonTypes.NONE,45),
        BELLSPROUT("Bellsprout",50,	75,	35,	70,	30,	40, PokemonTypes.POISON, PokemonTypes.GRASS,255),
        WEEPINBELL("Weepinbell",65,	90,	50,	85,	45	,55, PokemonTypes.POISON, PokemonTypes.GRASS,120),
        VICTREEBEL("Victorybel",80,	105,	65,	100	,70	,70, PokemonTypes.POISON, PokemonTypes.GRASS,45),
        TENTACOOL("Tentacool",40,	40,	35,	50	,100,	70, PokemonTypes.POISON, PokemonTypes.WATER,190),
        TENTACRUEL("Tentacrule",80,	70,	65,	80	,120,	100, PokemonTypes.POISON, PokemonTypes.WATER,60),
        GEODUDE("Geodude",40	,80	,100	,30	,30	,20, PokemonTypes.ROCK, PokemonTypes.GROUND,255),
        GRAVELER("Graveler",55	,95	,115	,45,	45	,35, PokemonTypes.ROCK, PokemonTypes.GROUND,120),
        GOLEM("Golem",80	,120	,130	,55,	65	,45, PokemonTypes.ROCK, PokemonTypes.GROUND,45),
        PONYTA("Ponyta",50	,85	,55	,65,	65	,90, PokemonTypes.FIRE, PokemonTypes.NONE,190),
        RAPIDASH("Rapidash",65	,100	,70	,80,	80	,105, PokemonTypes.FIRE, PokemonTypes.NONE,60),
        SLOWPOKE("Slowpoke",90	,65	,65	,40,	40	,15, PokemonTypes.WATER, PokemonTypes.PSYCHIC,190),
        SLOWBRO("Slowbro",95	,75	,110	,100,	80	,30, PokemonTypes.WATER, PokemonTypes.PSYCHIC,76),
        MAGNEMITE("Magnemite",25	,35,	70	,95,	55	,45, PokemonTypes.ELECTRIC, PokemonTypes.STEEL,190),
        MAGNETON("Magneton",50	,60	,95	,120,	70	,70, PokemonTypes.ELECTRIC, PokemonTypes.STEEL,60),
        FARFETCHD("Farfetched",52,	90,	55,	58,	62,	60, PokemonTypes.FLYING, PokemonTypes.NORMAL,45),
        DODUO("Doduo",35	,85	,45	,35	,35,	75, PokemonTypes.FLYING, PokemonTypes.NORMAL,190),
        DODRIO("Dodrio",60	,110,	70	,60	,60,	110, PokemonTypes.FLYING, PokemonTypes.NORMAL,45),
        SEEL("Seel",65	,45	,55	,45	,70	,45, PokemonTypes.WATER, PokemonTypes.NONE,190),
        DEWGONG("Dewgong",90,	70,	80,	70,	95,	70, PokemonTypes.WATER, PokemonTypes.ICE,75),
        GRIMER("Grimer",80	,80,	50,	40,	50,	25, PokemonTypes.POISON, PokemonTypes.NONE,190),
        MUK("Muk",105	,105	,75	,65	,100	,50, PokemonTypes.POISON, PokemonTypes.NONE,75),
        SHELLDER("Shellder",30,	65,	100,	45	,25,	40, PokemonTypes.WATER, PokemonTypes.NONE,190),
        CLOYSTER("Cloyster",50	,95,	180	,85	,45,	70, PokemonTypes.WATER, PokemonTypes.ICE,60),
        GASTLY("Gastly",30	,35,	30	,100	,35,	80, PokemonTypes.POISON, PokemonTypes.GHOST,190),
        HAUNTER("Haunter",45	,50,	45	,115	,55,	95, PokemonTypes.POISON, PokemonTypes.GHOST,90),
        GENGAR("Gengar",60	,65,	60	,130	,75,	110, PokemonTypes.POISON, PokemonTypes.GHOST,45),
        ONIX("Onix",35	,45	,160	,30	,45	,70, PokemonTypes.ROCK, PokemonTypes.GROUND,45),
        DROWZEE("Drowzee",60,	48	,45,	43,	90	,42, PokemonTypes.PSYCHIC, PokemonTypes.NONE,190),
        HYPNO("Hypno",85	,73	,70,	73,	115	,67, PokemonTypes.PSYCHIC, PokemonTypes.NONE,75),
        KRABBY("Krabby",30	,105	,90,	25,	25	,50, PokemonTypes.WATER, PokemonTypes.NONE,190),
        KINGLER("Kingler",55	,130	,115,	50,	50,	75, PokemonTypes.WATER, PokemonTypes.NONE,60),
        VOLTORB("Voltorb",40	,30	,50	,55	,55	,100, PokemonTypes.ELECTRIC, PokemonTypes.NONE,190),
        ELECTRODE("Electrobe",60,	50	,70,	80,	80,	150, PokemonTypes.ELECTRIC, PokemonTypes.NONE,60),
        EXEGGCUTE("Exeggcute",60	,40,	80	,60	,45,	40, PokemonTypes.GRASS, PokemonTypes.PSYCHIC,90),
        EXEGGUTOR("Exeggutor",95	,95,	85	,125,	75,	55, PokemonTypes.GRASS, PokemonTypes.PSYCHIC,45),
        CUBONE("Cubone",50	,50	,95	,40	,50,	35, PokemonTypes.GROUND, PokemonTypes.NONE,190),
        MAROWAK("Marowak",60	,80	,110	,50	,80	,45, PokemonTypes.GROUND, PokemonTypes.NONE,75),
        HITMONLEE("Hitmonlee",50,	120,	53,	35,	110,	87, PokemonTypes.FIGHTING, PokemonTypes.NONE,45),
        HITMONCHAN("Hitmonchan",50,	105,	79,	35,	110,	76, PokemonTypes.FIGHTING, PokemonTypes.NONE,45),
        LICKITUNG("Lickitung",90,	55,	75,	60,	75	,30, PokemonTypes.NORMAL, PokemonTypes.NONE,45),
        KOFFING("Koffing",40	,65	,95	,60	,45,	35, PokemonTypes.POISON, PokemonTypes.NONE,190),
        WEEZING("Weezing",65	,90	,120	,85	,70,	60, PokemonTypes.POISON, PokemonTypes.NONE,60),
        RHYHORN("Rhyhorn",80	,85	,95	,30	,30,	25, PokemonTypes.ROCK, PokemonTypes.GROUND,120),
        RHYDON("Rhydon",105	,130	,120	,45	,45,	40, PokemonTypes.ROCK, PokemonTypes.GROUND,160),
        CHANSEY("Chansey",250	,5	,5	,35	,105,	50, PokemonTypes.NORMAL, PokemonTypes.NONE,30),
        TANGELA("Tanglea",65	,55	,115	,100	,40	,60, PokemonTypes.GRASS, PokemonTypes.NONE,45),
        KANGASKHAN("Kangaskhan",105,	95,	80,	40,	80,	90, PokemonTypes.NORMAL, PokemonTypes.NONE,45),
        HORSEA("Horsea",30	,40	,70	,70	,25	,60, PokemonTypes.WATER, PokemonTypes.NONE,255),
        SEADRA("Seadra",55	,65	,95	,95	,45	,85, PokemonTypes.WATER, PokemonTypes.NONE,75),
        GOLDEEN("Goldeen",45	,67	,60	,35	,50	,63, PokemonTypes.WATER, PokemonTypes.NONE,255),
        SEAKING("Seaking",80	,92	,65	,65	,80	,68, PokemonTypes.WATER, PokemonTypes.NONE,60),
        STARYU("Staryu",30	,45	,55	,70	,55	,85, PokemonTypes.WATER, PokemonTypes.NONE,255),
        STARMIE("Starmie",60	,75	,85	,100	,85	,115, PokemonTypes.WATER, PokemonTypes.PSYCHIC,60),
        MR_MIME("Mr.Mime",40	,45	,65	,100	,120,	90, PokemonTypes.PSYCHIC, PokemonTypes.FAIRY,45),
        SCYTHER("Scyther",70	,110,	80,	55	,80,	105, PokemonTypes.BUG, PokemonTypes.FLYING,45),
        JYNX("Jynx",65	,50	,35	,115,	95	,95, PokemonTypes.ICE, PokemonTypes.PSYCHIC,45),
        ELECTABUZZ("Electabuzz",65,	83,	57	,95	,85	,105, PokemonTypes.ELECTRIC, PokemonTypes.NONE,45),
        MAGMAR("Magmar",65	,95	,57	,100,	85,	93, PokemonTypes.FIRE, PokemonTypes.NONE,45),
        PINSIR("Pinsir",65,	125	,100,	55	,70,	85, PokemonTypes.BUG, PokemonTypes.NONE,45),
        TAUROS("Tauros",75,	100	,95	,40	,70	,110, PokemonTypes.NORMAL, PokemonTypes.NONE,45),
        MAGIKARP("Magikarp",20,	10,	55,	15,	20,	80, PokemonTypes.WATER, PokemonTypes.NONE,255),
        GYARADOS("Gyrados",95	,125	,79	,60	,100	,81, PokemonTypes.WATER, PokemonTypes.NONE,45),
        LAPRAS("Lapras",130	,85	,80	,85	,95	,60, PokemonTypes.WATER, PokemonTypes.NONE,45),
        DITTO("Ditto",48	,48	,48	,48	,48	,48, PokemonTypes.NORMAL, PokemonTypes.NONE,35),
        EEVEE("Eevee",55	,55	,50	,45	,65	,55, PokemonTypes.NORMAL, PokemonTypes.NONE,45),
        VAPOREON("Vaporeon",130,	65,	60,	110,	95,	65, PokemonTypes.WATER, PokemonTypes.NONE,45),
        JOLTEON("Jolteon",65	,65	,60	,110	,95	,130, PokemonTypes.ELECTRIC, PokemonTypes.NONE,45),
        FLAREON("Flareon",65	,130	,60	,95	,110	,65, PokemonTypes.FIRE, PokemonTypes.NONE,45),
        PORYGON("Porygon",65	,60	,70	,85	,75	,40, PokemonTypes.NORMAL, PokemonTypes.NONE,45),
        OMANYTE("Omanyte",35	,40	,100	,90	,55	,35, PokemonTypes.WATER, PokemonTypes.ROCK,45),
        OMASTAR("Omastar",70	,60	,125	,115	,70	,55, PokemonTypes.WATER, PokemonTypes.ROCK,45),
        KABUTO("Kabuto",30	,80	,90	,55	,45	,55,PokemonTypes.WATER, PokemonTypes.ROCK ,45),
        KABUTOPS("Kabutops",60	,115	,105	,65	,70	,80, PokemonTypes.WATER, PokemonTypes.ROCK,45),
        AERODACTYL("Aerodactyl",80,	105,	65,	60,	75,	130, PokemonTypes.ROCK, PokemonTypes.FLYING,45),
        SNORLAX("Snorlax",160	,110	,65	,65	,110	,30, PokemonTypes.NORMAL, PokemonTypes.NONE,45),
        ARTICUNO("Articuno",90	,85	,100	,95	,125	,85 ,PokemonTypes.FLYING,PokemonTypes.ICE,3),
        ZAPDOS("Zapdos",90	,90	,85	,125	,90	,100, PokemonTypes.FLYING, PokemonTypes.ELECTRIC,3),
        MOLTRES("Moltres",90	,100	,90	,125	,85	,90,  PokemonTypes.FLYING, PokemonTypes.FIRE,3),
        DRATINI("Dratini",41	,64	,45	,50	,50,	50,  PokemonTypes.DRAGON, PokemonTypes.NONE,45),
        DRAGONAIR("Dragonair",61,	84,	65,	70	,70	,70, PokemonTypes.DRAGON, PokemonTypes.NONE,45),
        DRAGONITE("Dragonite",91,	134,	95,	100,	100	,80, PokemonTypes.DRAGON, PokemonTypes.FLYING,45),
        MEWTWO("Mewtwo",106	,110,	90	,154,	90	,130, PokemonTypes.PSYCHIC, PokemonTypes.NONE,3),
        MEW("Mew",100	,100	,100	,100,	100	,100, PokemonTypes.PSYCHIC, PokemonTypes.NONE,45);

        String NAME;

        int BASE_HP;
        int BASE_ATTACK;
        int BASE_DEFENSE;
        int BASE_SPECIAL_ATTACK;
        int BASE_SPECIAL_DEFENSE;
        int BASE_SPEED;

        int CATCHRATE;

        int GENDERRATIO;

        PokemonTypes POKEMONTYPE1;
        PokemonTypes POKEMONTYPE2;

       Species(String name, int base_hp, int base_attack, int base_defence, int base_special_attack, int base_special_defence, int base_speed, PokemonTypes pokemonType1, PokemonTypes pokemonType2, int catchrate){
            this.NAME = name;
            this.BASE_HP = base_hp;
            this.BASE_ATTACK = base_attack;
            this.BASE_DEFENSE = base_defence;
            this.BASE_SPECIAL_ATTACK = base_special_attack;
            this.BASE_SPECIAL_DEFENSE = base_special_defence;
            this.BASE_SPEED = base_speed;
            this.POKEMONTYPE1 = pokemonType1;
            this.POKEMONTYPE2 = pokemonType2;
            this.CATCHRATE = catchrate;
       }

    }

}

