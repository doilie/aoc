package aoc2015.day7;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BitwiseCircuit
{
    private final Set<BitwiseLogicGateOperation> bitwiseLogicGateOperationSet = new HashSet<>();

    BitwiseCircuit(String[] bitwiseOperationAssignments)
    {
        bitwiseLogicGateOperationSet.addAll(Arrays.stream(bitwiseOperationAssignments).filter(s -> !s.isEmpty()).map(BitwiseLogicGateOperation::new).collect(Collectors.toSet()));
    }

    void runCircuit()
    {
        Set<BitwiseLogicGateOperation> operationsWithoutResults = bitwiseLogicGateOperationSet.stream().filter(b -> !b.hasResult()).collect(Collectors.toSet());
        Set<BitwiseLogicGateOperation> operationsWithResults = bitwiseLogicGateOperationSet.stream().filter(BitwiseLogicGateOperation::hasResult).collect(Collectors.toSet());
        while(!operationsWithoutResults.isEmpty())
        {
            for (BitwiseLogicGateOperation operationWithResult : operationsWithResults)
            {
                String id = operationWithResult.getId();
                Set<BitwiseLogicGateOperation> op1IdToSet = operationsWithoutResults.stream().filter(b -> null != b.getOp1Id() && b.getOp1Id().equals(id)).collect(Collectors.toSet());
                op1IdToSet.forEach(b -> b.setOp1(operationWithResult.getResult()));
                Set<BitwiseLogicGateOperation> op2IdToSet = operationsWithoutResults.stream().filter(b -> null != b.getOp2Id() && b.getOp2Id().equals(id)).collect(Collectors.toSet());
                op2IdToSet.forEach(b -> b.setOp2(operationWithResult.getResult()));
            }
            operationsWithoutResults.forEach(BitwiseLogicGateOperation::performOperation);

            operationsWithResults = bitwiseLogicGateOperationSet.stream().filter(BitwiseLogicGateOperation::hasResult).collect(Collectors.toSet());
            operationsWithoutResults = bitwiseLogicGateOperationSet.stream().filter(b -> !b.hasResult()).collect(Collectors.toSet());
        }
    }

    String getResult(String id)
    {
        Optional<BitwiseLogicGateOperation> bitwiseLogicGateOperation = bitwiseLogicGateOperationSet.stream().filter(b -> b.getId().equals(id)).findFirst();
        if (bitwiseLogicGateOperation.isPresent())
        {
            return bitwiseLogicGateOperation.get().toString();
        }
        else
        {
            return id + ": not found";
        }
    }

    void setResult(String id, int result)
    {
        Optional<BitwiseLogicGateOperation> bitwiseLogicGateOperation = bitwiseLogicGateOperationSet.stream().filter(b -> b.getId().equals(id)).findFirst();
        bitwiseLogicGateOperation.ifPresent(logicGateOperation -> logicGateOperation.setResult(result));
    }
}
