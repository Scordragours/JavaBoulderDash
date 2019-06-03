package model;

/**
 * Enumerates every possible type of entity.
 *
 * @author DENEUVE GREGORY AND CANDAT ETIENNE
 */
public enum EntityType {
    /** Diamond */
    DIAMOND,
    /** Rolling Rock */
    ROLLINGROCK,
    /** Wall breakable */
    WALL,
    /** Exit type */
    EXIT,
    /** Dirt type */
    DIRT,
    /** Player type */
    PLAYER,
    /** Enemy with diamond reward */
    ENEMYDIAMOND,
    /** Enemy with point reward */
    ENEMYPOINT,
    /** Wall unbreakable */
    OUTLINE
}
