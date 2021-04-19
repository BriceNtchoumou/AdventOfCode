
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Their handheld game console won't turn on! They ask if you can take a look.
 *
 * You narrow the problem down to a strange infinite loop in the boot code (your puzzle input) of the device.
 * You should be able to fix it, but first you need to be able to run the code in isolation.
 *
 * The boot code is represented as a text file with one instruction per line of text.
 * Each instruction consists of an operation (acc, jmp, or nop) and an argument (a signed number like +4 or -20).
 *
 * acc increases or decreases a single global value called the accumulator by the value given in the argument.
 * For example, acc +7 would increase the accumulator by 7. The accumulator starts at 0.
 * After an acc instruction, the instruction immediately below it is executed next.
 * jmp jumps to a new instruction relative to itself. The next instruction
 * to execute is found using the argument as an offset from the jmp instruction; for example,
 * jmp +2 would skip the next instruction, jmp +1 would continue to the instruction
 * immediately below it, and jmp -20 would cause the instruction 20 lines above to be executed next.
 * nop stands for No OPeration - it does nothing. The instruction immediately below it is executed next.
 */


public class Day8 {

    public static void main(String[] args) throws IOException {
      //  final List<String> lines = Files.lines(Path.of("C:\\Users\\pontd\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\inputDay8.txt")).collect(Collectors.toUnmodifiableList());
          final List<String> lines = Files.lines(Path.of("C:\\Users\\pontd\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\test.txt")).collect(Collectors.toUnmodifiableList());

        // convert a List<String> in a ArrayList<String>
        List<String> inputFile = new ArrayList<String>(lines);

        // convert a ArrayList<String> in a ArrayList<Instruction>
        List<Instruction> instr = convertToArrayListInstuction((ArrayList<String>) inputFile);
        // Output the result of part One
         System.out.println("part One := " + runPart1((ArrayList<Instruction>) instr));
         System.out.println("part Two := " + runPart2((ArrayList<Instruction>) instr));






    }

    /**
     * However, if you change the second-to-last instruction (from jmp -4 to nop -4), the program terminates!
     * The instructions are visited in this order:
     *
     * nop +0  | 1
     * acc +1  | 2
     * jmp +4  | 3
     * acc +3  |
     * jmp -3  |
     * acc -99 |
     * acc +1  | 4
     * nop -4  | 5
     * acc +6  | 6
     *
     * After the last instruction (acc +6), the program terminates by attempting to run the instruction
     * below the last instruction in the file. With this change, after the program terminates,
     * the accumulator contains the value 8 (acc +1, acc +1, acc +6).
     *
     * Fix the program so that it terminates normally by changing exactly one jmp (to nop) or nop (to jmp).
     * What is the value of the accumulator after the program terminates?
     *
     */

    public static int runPart2(ArrayList<Instruction> arrayOfInstructions)
    {
        int accumulator = 0;
        int index = 0;
        boolean terminiert = false;
       ArrayList<Instruction>[] myArrayListInstruction = new ArrayList[arrayOfInstructions.size()];
       for(int i = 0; i < arrayOfInstructions.size(); i++)
       {

           myArrayListInstruction[i] = modifyInstructionOperation(arrayOfInstructions,i);

           while(!terminiert)
           {
                  boolean[] visited = new boolean[myArrayListInstruction.length];

                 while(!visited[index])
                 {
                     visited[index] = true;
                    String op = myArrayListInstruction[i].get(index).getOperation();

                    switch(op)
                    {
                        case "nop":
                        {
                            index++;
                            break;
                        }
                        case "jmp":
                        {
                            index += arrayOfInstructions.get(index).getArgument();
                            break;
                        }
                        case "acc":
                        {
                            accumulator += arrayOfInstructions.get(index).getArgument();
                            index++;
                            break;
                        }
                    }
                 }
                 if(index == arrayOfInstructions.size())
                 {
                     terminiert = true;
                 }
           }





       }




         return accumulator;
    }


    /**
     * Somewhere in the program, either a jmp is supposed to be a nop, or a nop is supposed to be a jmp.
     * (No acc instructions were harmed in the corruption of this boot code.)
     *
     * The program is supposed to terminate by attempting to execute an instruction
     * immediately after the last instruction in the file. By changing exactly one jmp or nop,
     * you can repair the boot code and make it terminate correctly.
     *
     *
     */
    public static ArrayList<Instruction> modifyInstructionOperation(ArrayList<Instruction> instruction,int index)
    {
        ArrayList<Instruction> instructions = instruction;
        String value = instruction.get(index).getOperation();

        switch(value)
        {
            case "nop": {
                instructions.get(index).setOperation("jmp");
                break;
            }
            case "jmp": {
                instructions.get(index).setOperation("nop");
                break;
            }

        }

        return instructions;
    }

    /**  Part One
     *  For example, consider the following program:
     *
     * nop +0
     * acc +1
     * jmp +4
     * acc +3
     * jmp -3
     * acc -99
     * acc +1
     * jmp -4
     * acc +6
     * These instructions are visited in this order:
     *
     * nop +0  | 1
     * acc +1  | 2, 8(!)
     * jmp +4  | 3
     * acc +3  | 6
     * jmp -3  | 7
     * acc -99 |
     * acc +1  | 4
     * jmp -4  | 5
     * acc +6  |
     * First, the nop +0 does nothing. Then, the accumulator is increased from 0 to 1 (acc +1) and
     * jmp +4 sets the next instruction to the other acc +1 near the bottom.
     * After it increases the accumulator from 1 to 2, jmp -4 executes, setting the next instruction
     * to the only acc +3. It sets the accumulator to 5, and jmp -3 causes the program to
     * continue back at the first acc +1.
     *
     * This is an infinite loop: with this sequence of jumps, the program will run forever.
     * The moment the program tries to run any instruction a second time, you know it will never terminate.
     *
     * Immediately before the program would run an instruction a second time, the value in the accumulator is 5.
     *
     * Run your copy of the boot code. Immediately before any instruction is executed a second time,
     * what value is in the accumulator?
     *
     */

    /**
     * The method convertToArrayListInstruction converts a ArrayList<String> Datatype in a
     *  ArrayList<Instruction> Datatype
     */
    public static ArrayList<Instruction> convertToArrayListInstuction (ArrayList<String> inputFile)
    {
        List<Instruction> instructions = new ArrayList<>();

        for(int index = 0; index < inputFile.size(); index++)
        {
            Instruction instruction = new Instruction(inputFile.get(index).substring(0,3),
                    Integer.parseInt(inputFile.get(index).substring(4)));

            instructions.add(instruction);

        }
        return (ArrayList<Instruction>) instructions;

    }

    /**
     * This method calculates the current value in the accumulator
     *
     */
    public static int runPart1(ArrayList<Instruction> arrayOfInstructions)
    {
        int acc = 0;
        boolean [] visited = new boolean[arrayOfInstructions.size()];
        int index = 0;

        while(!visited[index])
        {
            visited[index] = true;
            String op = arrayOfInstructions.get(index).getOperation();

            switch(op)
            {
                case "nop":
                {
                    index++;
                    break;
                }
                case "jmp":
                {
                    index += arrayOfInstructions.get(index).getArgument();
                    break;
                }
                case "acc":
                {
                    acc += arrayOfInstructions.get(index).getArgument();
                    index++;
                    break;
                }

            }

        }
        return acc;

    }

}





class Instruction {
    String operation;
    int argument;

    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation)
    {
        this.operation = operation;
    }


    public int getArgument() {
        return argument;
    }


    public Instruction(String operation, int argument) {
        this.operation = operation;
        this.argument = argument;
    }



}