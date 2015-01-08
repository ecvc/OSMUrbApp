/*--------------------------------------------------------------------

Copyright Jonathan Cozzo and Patrick Rannou (22/03/2013)

This software is an Android application whose purpose is to select 
and characterize zones on a photography (type, material, color...).

This software is governed by the CeCILL license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the CeCILL
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL license and that you accept its terms.

-----------------------------------------------------------------------*/

package com.ecn.urbapp.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ecn.urbapp.activities.MainActivity;
import com.ecn.urbapp.db.Element;
import com.ecn.urbapp.fragments.CharacteristicsFragment;
import com.ecn.urbapp.zones.UtilCharacteristicsZone;
import com.example.osmurbapp.R;

/**
 * This class creates the dialog that indicate which pixelgeoms is not defined
 * 
 * @author Jules Party
 * 
 */
@SuppressLint("NewApi") public class SummaryDialogFragment extends DialogFragment {
	
	private long eltIdToSelect;
	private Dialog box;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		box = new Dialog(getActivity());
		box.setContentView(R.layout.layout_definition_dialog_recap);
		box.setTitle(R.string.definition_recap);
		LinearLayout recapList = (LinearLayout) box.findViewById(R.id.definition_recap_linear_layout);
		for (Element element : MainActivity.element) {
			if (element.getElementType_id() == 0 || element.getMaterial_id() == 0 || element.getElement_color() == null) {
				Button button = new Button(getActivity());
				button.setText("L'élément n°" + element.getElement_id() + " n'est pas entièrement défini.");
				eltIdToSelect = element.getElement_id();
				button.setOnClickListener(new OnClickListener() {

					private long idToSelect = eltIdToSelect;

					@Override
					public void onClick(View v) {
						UtilCharacteristicsZone.unselectAll();
						Element element = UtilCharacteristicsZone.getElementFromId(idToSelect);
						element.setSelected(true);
						for (Element elt : element.getLinkedElement()) {
							elt.setSelected(true);
						}
						CharacteristicsFragment.getMyImage().invalidate();
						box.dismiss();
					}
				});
				recapList.addView(button);
			}
		}
		return box;
	}

	@Override
	public void onCancel(DialogInterface dialog) {
	}
}