package com.AiVoiceNPCs;

import java.util.HashMap;
import java.util.Map;

public class NPCVoiceVariationMap {
    public static int mapVariation (int npcId) {
        Map<Integer, Integer> npcVariations = new HashMap<Integer, Integer>();
        npcVariations.put(385, 1); //Man
        npcVariations.put(1118, 2); //Man
        npcVariations.put(1138, 3); //Man
        npcVariations.put(3014, 4); //Man
        npcVariations.put(3106, 1); //Man
        npcVariations.put(3107, 2); //Man
        npcVariations.put(3108, 3); //Man
        npcVariations.put(3109, 4); //Man
        npcVariations.put(3110, 1); //Man
        npcVariations.put(3261, 2); //Man
        npcVariations.put(3264, 3); //Man
        npcVariations.put(3265, 4); //Man
        npcVariations.put(3298, 1); //Man
        npcVariations.put(3652, 2); //Man
        npcVariations.put(4268, 3); //Man
        npcVariations.put(4269, 4); //Man
        npcVariations.put(4270, 1); //Man
        npcVariations.put(4271, 2); //Man
        npcVariations.put(4272, 3); //Man
        npcVariations.put(6776, 4); //Man
        npcVariations.put(6815, 1); //Man
        npcVariations.put(6818, 2); //Man
        npcVariations.put(6987, 3); //Man
        npcVariations.put(6988, 4); //Man
        npcVariations.put(6989, 1); //Man
        npcVariations.put(7281, 2); //Man
        npcVariations.put(7919, 3); //Man
        npcVariations.put(7920, 4); //Man
        npcVariations.put(8858, 1); //Man
        npcVariations.put(8859, 2); //Man
        npcVariations.put(8860, 3); //Man
        npcVariations.put(8861, 4); //Man
        npcVariations.put(8862, 1); //Man
        npcVariations.put(10672, 2); //Man
        npcVariations.put(10673, 3); //Man
        npcVariations.put(10945, 4); //Man
        npcVariations.put(11032, 1); //Man
        npcVariations.put(11057, 2); //Man
        npcVariations.put(11058, 3); //Man
        npcVariations.put(1119, 1); //Woman
        npcVariations.put(1130, 2); //Woman
        npcVariations.put(1131, 3); //Woman
        npcVariations.put(1140, 4); //Woman
        npcVariations.put(1141, 1); //Woman
        npcVariations.put(1142, 2); //Woman
        npcVariations.put(3015, 3); //Woman
        npcVariations.put(3111, 4); //Woman
        npcVariations.put(3112, 1); //Woman
        npcVariations.put(3113, 2); //Woman
        npcVariations.put(3268, 3); //Woman
        npcVariations.put(3299, 4); //Woman
        npcVariations.put(4958, 1); //Woman
        npcVariations.put(6990, 2); //Woman
        npcVariations.put(6991, 3); //Woman
        npcVariations.put(6992, 4); //Woman
        npcVariations.put(7921, 1); //Woman
        npcVariations.put(7922, 2); //Woman
        npcVariations.put(8863, 3); //Woman
        npcVariations.put(8864, 4); //Woman
        npcVariations.put(10674, 1); //Woman
        npcVariations.put(10728, 2); //Woman
        npcVariations.put(11053, 3); //Woman
        npcVariations.put(11054, 4); //Woman

        if (npcVariations.containsKey(npcId)) {
            return npcVariations.get(npcId);
        } else {
            return 1;
        }
    }
}
