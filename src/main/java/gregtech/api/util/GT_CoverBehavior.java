package gregtech.api.util;

import gregtech.api.GregTech_API;
import gregtech.api.interfaces.tileentity.ICoverable;
import gregtech.api.objects.SimpleItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;

import static gregtech.api.GT_Values.E;

/**
 * For Covers with a special behavior.
 */
public abstract class GT_CoverBehavior {
    /**
     * Called by updateEntity inside the covered TileEntity. aCoverVariable is the Value you returned last time.
     */
    public int doCoverThings(EnumFacing aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity, long aTimer) {
        return aCoverVariable;
    }

    /**
     * Called when someone rightclicks this Cover.
     * <p/>
     * return true, if something actually happens.
     */
    public boolean onCoverRightclick(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        return false;
    }

    /**
     * Called when someone rightclicks this Cover Client Side
     * <p/>
     * return true, if something actually happens.
     */
    public boolean onCoverRightclickClient(EnumFacing aSide, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        return false;
    }

    /**
     * Called when someone rightclicks this Cover with a Screwdriver. Doesn't call @onCoverRightclick in this Case.
     * <p/>
     * return the new Value of the Cover Variable
     */
    public int onCoverScrewdriverclick(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
        return aCoverVariable;
    }

    /**
     * Checks if the Cover can be placed on this.
     */
    public boolean isCoverPlaceable(EnumFacing aSide, SimpleItemStack aStack, ICoverable aTileEntity) {
        return true;
    }

    /**
     * Removes the Cover if this returns true, or if aForced is true.
     * Doesn't get called when the Machine Block is getting broken, only if you break the Cover away from the Machine.
     */
    public boolean onCoverRemoval(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, boolean aForced) {
        return true;
    }

    /**
     * Gives a small Text for the status of the Cover.
     */
    public String getDescription(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return E;
    }

    /**
     * How Blast Proof the Cover is. 30 is normal.
     */
    public float getBlastProofLevel(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return 10.0F;
    }

    /**
     * If it lets RS-Signals into the Block
     * <p/>
     * This is just Informative so that Machines know if their Redstone Input is blocked or not
     */
    public boolean letsRedstoneGoIn(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return false;
    }

    /**
     * If it lets RS-Signals out of the Block
     */
    public boolean letsRedstoneGoOut(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return false;
    }

    /**
     * If it lets Energy into the Block
     */
    public boolean letsEnergyIn(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return false;
    }

    /**
     * If it lets Energy out of the Block
     */
    public boolean letsEnergyOut(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return false;
    }

    /**
     * If it lets Liquids into the Block, aFluid can be null meaning if this is generally allowing Fluids or not.
     */
    public boolean letsFluidIn(EnumFacing aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity) {
        return false;
    }

    /**
     * If it lets Liquids out of the Block, aFluid can be null meaning if this is generally allowing Fluids or not.
     */
    public boolean letsFluidOut(EnumFacing aSide, int aCoverID, int aCoverVariable, Fluid aFluid, ICoverable aTileEntity) {
        return false;
    }

    /**
     * If it lets Items into the Block, aSlot = -1 means if it is generally accepting Items (return false for no Interaction at all), aSlot = -2 means if it would accept for all Slots (return true to skip the Checks for each Slot).
     */
    public boolean letsItemsIn(EnumFacing aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity) {
        return false;
    }

    /**
     * If it lets Items out of the Block, aSlot = -1 means if it is generally accepting Items (return false for no Interaction at all), aSlot = -2 means if it would accept for all Slots (return true to skip the Checks for each Slot).
     */
    public boolean letsItemsOut(EnumFacing aSide, int aCoverID, int aCoverVariable, int aSlot, ICoverable aTileEntity) {
        return false;
    }

    /**
     * If it lets you rightclick the Machine normally
     */
    public boolean isGUIClickable(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return false;
    }

    /**
     * Needs to return true for Covers, which have a Redstone Output on their Facing.
     */
    public boolean manipulatesSidedRedstoneOutput(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return false;
    }

    /**
     * if this Cover should let Pipe Connections look connected even if it is not the case.
     */
    public boolean alwaysLookConnected(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return false;
    }

    /**
     * Called to determine the incoming Redstone Signal of a Machine.
     * Returns the original Redstone per default.
     * The Cover should @letsRedstoneGoIn or the aInputRedstone Parameter is always 0.
     */
    public byte getRedstoneInput(EnumFacing aSide, byte aInputRedstone, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return letsRedstoneGoIn(aSide, aCoverID, aCoverVariable, aTileEntity) ? aInputRedstone : 0;
    }

    /**
     * Gets the Tick Rate for doCoverThings of the Cover
     * <p/>
     * 0 = No Ticks! Yes, 0 is Default, you have to override this
     */
    public int getTickRate(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return 0;
    }

    /**
     * If this is a simple Cover, which can also be used on Bronze Machines and similar.
     */
    public boolean isSimpleCover() {
        return false;
    }

    /**
     * The MC Color of this Lens. -1 for no Color (meaning this isn't a Lens then).
     */
    public byte getLensColor(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        return -1;
    }

    /**
     * @return the ItemStack dropped by this Cover
     */
    public ItemStack getDrop(EnumFacing aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity) {
        SimpleItemStack stack = GregTech_API.getCoverItem(aCoverID);
        if(stack == null) return null;
        return stack.toStack();
    }

    /**
     * @return sets the Cover upon placement.
     */
    public void placeCover(EnumFacing aSide, int aCoverID, ItemStack aCover, ICoverable aTileEntity) {
        aTileEntity.setCoverIDAtSide(aSide, aCoverID);
    }

}