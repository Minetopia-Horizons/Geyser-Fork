/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.inventory;

import org.geysermc.geyser.session.GeyserSession;
import org.geysermc.mcprotocollib.protocol.data.game.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantOptionData;
import lombok.Getter;

@Getter
public class EnchantingContainer extends Container {
    /**
     * A cache of what Bedrock sees
     */
    private final EnchantOptionData[] enchantOptions;
    /**
     * A mutable cache of what the server sends us
     */
    private final GeyserEnchantOption[] geyserEnchantOptions;

    public EnchantingContainer(GeyserSession session, String title, int id, int size, ContainerType containerType) {
        super(session, title, id, size, containerType);

        enchantOptions = new EnchantOptionData[3];
        geyserEnchantOptions = new GeyserEnchantOption[3];
        for (int i = 0; i < geyserEnchantOptions.length; i++) {
            geyserEnchantOptions[i] = new GeyserEnchantOption(i);
            // Options cannot be null, so we build initial options
            // GeyserConnection can be safely null here because it's only needed for net IDs
            enchantOptions[i] = geyserEnchantOptions[i].build(null);
        }
    }
}
