package seng201.team0.models.NPCs;

import seng201.team0.services.GameService;
import seng201.team0.util.Dialogue.NpcDialogue;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * AllNPCs class contains hardcoded NPCs, each NPC is represented by the NPC object
 */
public class AllNPCs {

    private static final NPC CHAMPION = new NPC("Champion",
            Map.of(
                    "0", new String[]{
                            "So you've made it this far...",
                            "This track isn't for newcomers, so be ready to adapt quick.",
                            "Pick your first few cars to get yourself into the race.",
                            "They're nothing fancy, but they'll handle your first few runs just fine.",
                            "I should get going, there's always another trophy up for grabs.",
                            "Good luck out there. Let's see if you’ve got what it takes to be called... 'Driver'."
                    },
                    "1", new String[]{
                            "Well done on getting set up!",
                            "THIS IS BIG MONEY RACING, WHERE THE RACES ARE WILD AND THE PRIZES ARE EVEN WILDER!",
                            "I'm the Champion, and I am a big deal around here, I also run some of the races and as you know we take the action all over the world.",
                            "While you're here, check out the Shop, it's where you can buy new cars and parts to upgrade your cars... We only let racers buy ",
                            "There's also the Garage, where you can sell parts, view and select your cars, and fine tune your setup.",
                            "Good luck out there... make a name for yourself or 'DIE' trying!"
                    },
                    "2", new String[]{
                            "Congrats on finishing your first few seasons with us!",
                            "The developers of the race wanted me to thank you on their behalf for playing their game",
                            "We'll see you on the track soon!"
                    }
            ), "/Assets/NPCs/Champion.png",
            null);

    private static final NPC FUELONA = new NPC(
            "Fuelona",
            Map.of(
                    "race", new String[]{
                            "Hey... didn’t expect to see you here.",
                            "Name’s Fuelona. I run the old fuel station out here.",
                            "Not much traffic these days. Feels like I’m running on empty too.",
                            "Dad keeps talking about a cat that shows up at night... says it stares at real funny.",
                            "But hey, oh well. Life goes on.",
                            "Good luck out there. Show ’em what you’ve got."
                    },
                    "shop", new String[]{
                            "Well, well, if it isn't my favorite speedster...",
                            "Still playing the strong, silent type, huh?",
                            "Why not treat yourself to some parts? Your ride could use a little... enhancement."
                    },
                    "garage", new String[]{
                            "Nice lineup you've got here...",
                            "Bet my dad would be all over this, he'd probably say it takes him back to his wild days.",
                            "You might've seen him around... crawling through here earlier, trying to relive his glory days.",
                            "Anyway, once your season wraps up, how about we take a spin together? Just the two of us."
                    }
            ),
            "/Assets/NPCs/Fuelona.png",
            null
    );

    private static final NPC MAN_WITH_SUITCASE = new NPC("Man with a Suitcase",
            Map.of(
                    "race", new String[]{
                            "...", "...",
                            "Hey there, " + GameService.getInstance().getPlayer().getName().substring(0, 3) + "...",
                            "Old habits die hard. Been at this a long time.",
                            "I can tell you’re a racer. I was one too, once.",
                            "These races attract all kinds, not just people.",
                            "There’s something buzzing beneath it all. You feel it?",
                            "Ever see a shadow move wrong? Hear engines rev when they shouldn’t?",
                            "No? Maybe I’ve been on the road too long.",
                            "But that cat people whisper about? Watch for it.",
                            "Anyway, forget I said anything. Just some guy with a suitcase.",
                            "Good luck..."
                    },
                    "shop", new String[]{
                            "...","..."
                    },
                    "garage", new String[]{
                            "...","..."
                    }
            ),
            "/Assets/NPCs/ManWithBriefCase.png",
            null
    );

    private static final NPC RUSTY_RICK = new NPC("Rusty Rick",
            Map.of(
                    "race", new String[]{
                            "They call me... *hic*... Rusty Rick. Or was it Slippery Sam? *burp* Mighta been yesterday.",
                            "Y’ever hear a cat talk? Not just any cat... this one sings... *meow*... oh yeah, she sings real pretty.",
                            "*hic*... Betsy said she saw the moon fall last night. Swear it on my rusty wrench! *clang*",
                            "Road’s been whisperin’ again. You hear it too? Or is that just... *shhh*... the wind?"
                        },
                    "shop", new String[]{
                            "You see that old van in the shop yet? heh heh",
                            "Trust me, kid... ya don’t want that van. Nooo sir...",
                            "Good ol’ Rusty Rick only wants the best for ya. *hic*... or was it the worst? *burp*",
                            "On my blue crystals!, I swear... that camper van’s gonna break real bad! faster than a two-legged chair."
                        },
                    "garage", new String[]{
                            "Well, well... would ya look at that beast...",
                            "Takes a real eye to pick a beauty like that. A real sharp eye... or a blind one. *hic*",
                            "You... you remind me of someone... yeah... young Rick. Before the rust set in...",
                            "*sigh*... Not what I am now... no sir..."
            }

        ),
            "/Assets/NPCs/RustyRick.png",
            null);

    private static final NPC SLICK_SALLY = new NPC("Slick Sally",
            Map.of(
                    "race", new String[]{
                            "Hope them upgrades do the trick... or at least keep ya from crashin’."
                    },
                    "shop", new String[]{
                            "Need an upgrade? I gotcha.",
                            "Just tell me what you need and I’ll make it shine.",
                            "Oh, and if you see... her... my car I mean... let me know. Just wanna see her one more time."
                    },
                    "garage", new String[]{
                            "Heyo, Slick Sally here. Nobody drifts like me... well, used to.",
                            "Sold my ride to cover the bills. Still hurts.",
                            "One day, I'll get her back... unless someone else snags her first.",
                            "Funny... that car of yours... reminds me of her."
                    }
            ), "/Assets/NPCs/SlickSally.png",
            null);

    private static final NPC WHISKERS = new NPC("Whiskers",
            Map.of(
                    "race", new String[]{
                            "Meow, Meow, Meeeooow!", "Finally, I don't need to pretend to meow anymore",
                            "are you confused?", "Don't be this race is full of strange things",
                            "Oh and by the way, " + GameService.getInstance().getPlayer().getName() + "...",
                            "If you tell anyone I can speak",
                            "Nobody will believe you, you know why?", "Because you are just a 'Driver'"
                    },
                    "shop", new String[]{
                            "Did you see him?",
                            "My uncle...",
                            "I swear he was somewhere 'round here."
                    },
                    "garage", new String[]{
                            "Meow, meow... Meeeooow!",
                            "Meow, meow...",
                            "Hope that ain’t YOUR horse and wagon...",
                            "Meeooow..."
                    }
            ), "/Assets/NPCs/Whiskers.png",
            null);


    private static final NPC MEDVED = new NPC("Medved",
            Map.of(
                    "race", new String[]{
                            "Здравствуйте, водитель. Вы говорите по-русски?",
                            ".....",
                            "по-английски? OK... english... *takes a long drag of a cigarette*",
                            "All... racers... must... eh...",
                            "how you say... покажи мне...",
                            "pres..",
                            "press? no... da, present!",
                            "YES! Present me... документики!",
                            "All drivers должны present documents... for entry into наш Motherland!",
                            "Or else... ну, you don’t want to end up in Siberia, да?"
                    }
            ), "/Assets/NPCs/Bear.png",
            null);

    private static final NPC EAGLE = new NPC("Marvin Beak",
            Map.of(
                    "race", new String[]{
                            "You hear that? The sound of money changing hands – quiet, but deafening. If you catch my drift",
                            "Everyone's talkin' about racing for glory. Nah, kid, They’re racing for me.",
                            "A little nudge here, a whispered word there... and before you know it, everyone's sittin' in my pocket, ya dig?",
                            "The road’s a game of odds. I deal the hand. And the house... well, the house always collects."
                    }
            ), "/Assets/NPCs/Eagle.png",
            null);

    private static final NPC NEKO = new NPC("Maneki Neko",
            Map.of(
                    "race", new String[]{
                            "Welcome! I am Maneki Neko, the lucky cat.",
                            "In Japan, we believe fortune smiles on those who race with heart.",
                            "May your journey be swift and your spirit strong!"
                    }
            ), "/Assets/NPCs/Neko.png",
            null);

    public static NPC getChampion() {
        return CHAMPION;
    }
    public static NPC getFuelonaNPC() {
        return FUELONA;
    }
    public static NPC getManWithASuitcaseNPC() {
        return MAN_WITH_SUITCASE;
    }
    public static NPC getRustyRickNPC() {
        return RUSTY_RICK;
    }
    public static NPC getSlickSallyNPC() {
        return SLICK_SALLY;
    }
    public static NPC getWhiskersNPC() {
        return WHISKERS;
    }
    public static NPC getMedvedNPC() { return MEDVED; }
    public static NPC getEagleNPC() { return EAGLE; }
    public static NPC getNekoNPC() { return NEKO; }

    private static final List<NPC> allNPCs = List.of(
            FUELONA,
            MAN_WITH_SUITCASE,
            RUSTY_RICK,
            SLICK_SALLY,
            WHISKERS
    );


    /**
     * Shows dialogue from a random Npc using a keystring to access specific dialogue across all NPCs
     *
     * @param keyString Accesses the value of the associated key in the NPC dialogue map
     */
    public static void getRandomEncounter(String keyString) {
        Random random = new Random();
        double chance = random.nextDouble();

        if (chance < 0.27) {
            NPC npc = allNPCs.get(random.nextInt(allNPCs.size()));
            NpcDialogue.showDialogue(npc, keyString);
        }
    }
}